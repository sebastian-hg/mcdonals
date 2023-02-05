package com.mcdonalds.ecommerce.exception;

import com.mcdonalds.ecommerce.handlerErrors.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.util.StringUtils.hasText;

public class RepositoryException extends BaseException{

    public RepositoryException() {
        super();
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }

    @Override
    public Mono<ServerResponse> handlerException() {
        Throwable cause = getCause();
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = getMessage(this);

        if (cause != null) {
            String causeMessage = cause.getLocalizedMessage();
            if (hasText(causeMessage)) {
                message = causeMessage;
            }
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("RE_" + status)
                .errorDetail(message)
                .httpStatusCode(status)
                .build();

        return makeServerResponse(errorResponse);
    }
}