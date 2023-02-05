package com.mcdonalds.ecommerce.exception;


import com.mcdonalds.ecommerce.handlerErrors.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.util.StringUtils.hasText;

/**
 * es para Errores de api
 */
public class ApiException extends BaseException {

    private static final long serialVersionUID = 1L;

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    @Override
    public Mono<ServerResponse> handlerException() {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = getLocalizedMessage();
        String reason = null;
        ResponseStatus annotation = getClass().getAnnotation(ResponseStatus.class);
        if (annotation != null) {
            status = annotation.value();
            if (hasText(annotation.reason())) {
                reason = annotation.reason();
            }
        }

        if (!hasText(message)) {
            message = UNKNOWN_ERROR;
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("AP_" + status.value())
                .errorDetail(message)
                .httpStatusCode(status.value())
                .exceptionReason(reason)
                .build();
        return makeServerResponse(errorResponse);
    }
}