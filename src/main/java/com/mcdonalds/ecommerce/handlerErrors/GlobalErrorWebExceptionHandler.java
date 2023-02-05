package com.mcdonalds.ecommerce.Errors;

import com.mcdonalds.ecommerce.exception.BaseException;
import com.mcdonalds.ecommerce.exception.GatewayCustomException;
import com.mcdonalds.ecommerce.exception.GenericCustomException;
import com.mcdonalds.ecommerce.exception.WebClientCustomException;
import com.mcdonalds.ecommerce.exception.api.NotFoundException;
import com.mcdonalds.ecommerce.handlerErrors.GlobalErrorAttribute;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.PrematureCloseException;

import java.net.SocketException;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;

@Component
@Order(-2)
@Log4j2
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {
    private static final String NOT_FOUND_MESSAGE = "404 NOT_FOUND";
    private static final String UNKNOWN_ERROR = "Unknown error";
    private static final String GATEWAY_TIME_OUT = "Time out calling other service";

    public GlobalErrorWebExceptionHandler(
            GlobalErrorAttribute errorAttributes,
            WebProperties.Resources resources,
            ApplicationContext applicationContext,
            ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, resources, applicationContext);
        this.setMessageWriters(serverCodecConfigurer.getWriters());
    }


    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> errorPropertiesMap = getErrorAttributes(request,
                ErrorAttributeOptions.defaults());
        var throwable = (Throwable) errorPropertiesMap.get("throwable");
        Integer status = (Integer) errorPropertiesMap.get("status");
        log.error("catch error", throwable);

        if (throwable instanceof BaseException) {
            return ((BaseException) throwable).handlerException();
        }
        BaseException baseException = null;
        if (throwable instanceof WebClientResponseException) {
            baseException = new WebClientCustomException((WebClientResponseException) throwable);
        }

        if (throwable instanceof ReadTimeoutException) {
            baseException = new GatewayCustomException((ReadTimeoutException) throwable, GATEWAY_TIMEOUT.value(),
                    GATEWAY_TIME_OUT);
        }
        if (throwable instanceof PrematureCloseException || throwable instanceof SocketException) {
            baseException = new GatewayCustomException((Exception) throwable, BAD_GATEWAY.value(), UNKNOWN_ERROR);
        }

        if (status == 404) {
            baseException = new NotFoundException(NOT_FOUND_MESSAGE, throwable);
        }

        if (baseException == null) baseException = new GenericCustomException((Exception) throwable);

        return baseException.handlerException();
    }
}