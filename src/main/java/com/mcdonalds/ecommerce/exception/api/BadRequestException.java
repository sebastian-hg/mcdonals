package com.mcdonalds.ecommerce.exception.api;

import com.mcdonalds.ecommerce.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(value = BAD_REQUEST, reason = BadRequestException.MESSAGE)
public class BadRequestException extends ApiException {
    public static final transient String MESSAGE =
            "The client sent a request that this server could not understand.";
    private static final long serialVersionUID = -5136056121588225547L;

    public BadRequestException() {
        super(MESSAGE);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

}