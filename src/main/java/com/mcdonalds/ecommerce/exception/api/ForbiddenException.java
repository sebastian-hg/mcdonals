package com.mcdonalds.ecommerce.exception.api;

import com.mcdonalds.ecommerce.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;


@ResponseStatus(value = FORBIDDEN, reason = ForbiddenException.MESSAGE)
public class ForbiddenException extends ApiException {
    public static final transient String MESSAGE = "Authorization required.";
    private static final long serialVersionUID = 4297403547393623179L;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }
}