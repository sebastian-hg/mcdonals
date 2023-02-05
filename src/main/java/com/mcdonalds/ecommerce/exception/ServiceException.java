package com.mcdonalds.ecommerce.exception;

import com.mcdonalds.ecommerce.handlerErrors.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class ServiceException extends BaseException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    @Override
    public Mono<ServerResponse> handlerException() {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("SE_500")
                .errorDetail(getMessage(this))
                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return makeServerResponse(errorResponse);
    }
}