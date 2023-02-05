package com.mcdonalds.ecommerce.exception.api;

import com.mcdonalds.ecommerce.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(value = INTERNAL_SERVER_ERROR, reason = InternalServerErrorException.MESSAGE)
public class InternalServerErrorException extends ApiException {
    public static final transient String MESSAGE = "Something went wrong. Please try again later.";
    private static final long serialVersionUID = -5225093851450729438L;

    public InternalServerErrorException() {
        super();
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }
}