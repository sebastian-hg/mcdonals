package com.mcdonalds.ecommerce.exception.api;

import com.mcdonalds.ecommerce.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = NotFoundException.MESSAGE)
public class NotFoundException extends ApiException {
    public static final transient String MESSAGE = "The resource does not exist.";
    private static final long serialVersionUID = 171136765138743740L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}