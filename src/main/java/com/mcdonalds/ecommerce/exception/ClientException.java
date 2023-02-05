package com.mcdonalds.ecommerce.exception;

import com.mcdonalds.ecommerce.handlerErrors.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.util.StringUtils.hasText;

public class ClientException extends BaseException {

    public ClientException() {
        super();
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    @Override
    public Mono<ServerResponse> handlerException() {
        Throwable cause = getCause();
        if (cause instanceof WebClientResponseException) {
            WebClientCustomException exception = new WebClientCustomException((WebClientResponseException) cause);
            return exception.handlerException();
        }

        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = getMessage(this);

        if (cause != null) {
            String causeMessage = cause.getLocalizedMessage();
            if (hasText(causeMessage)) {
                message = causeMessage;
            }
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("CL_" + status)
                .errorDetail(message)
                .httpStatusCode(status)
                .build();
        return makeServerResponse(errorResponse);
    }
}