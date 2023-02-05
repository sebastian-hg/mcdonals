package com.mcdonalds.ecommerce.exception;

import com.mcdonalds.ecommerce.handlerErrors.response.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
public class GatewayCustomException extends BaseException {

    private final Exception exception;
    private final int status;
    private final String defaultMessage;

    @Override
    public Mono<ServerResponse> handlerException() {
        String message = getMessage(exception, defaultMessage);

        ErrorResponse errorResponse =
                ErrorResponse.builder()
                        .errorCode("GA_" + status)
                        .errorDetail(message)
                        .httpStatusCode(status)
                        .build();
        return makeServerResponse(errorResponse);
    }
}