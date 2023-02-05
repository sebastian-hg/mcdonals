package com.mcdonalds.ecommerce.exception;

import com.mcdonalds.ecommerce.handlerErrors.response.ErrorResponse;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.util.StringUtils.hasText;

public abstract class BaseException extends RuntimeException {
    public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";

    protected BaseException() {
        super();
    }

    protected BaseException(String message) {
        super(message);
    }

    protected BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    protected BaseException(Throwable cause) {
        super(cause);
    }

    public abstract Mono<ServerResponse> handlerException();

    protected String getMessage(Exception ex) {
        return getMessage(ex, UNKNOWN_ERROR);
    }

    protected String getMessage(Exception ex, String defaultMessage) {
        String message = ex.getLocalizedMessage();
        return !hasText(message) && hasText(defaultMessage) ? defaultMessage : message;
    }

    protected Mono<ServerResponse> makeServerResponse(ErrorResponse errorResponse) {
        return ServerResponse.status(errorResponse.getHttpStatusCode())
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorResponse));
    }

}