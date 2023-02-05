package com.mcdonalds.ecommerce.exception.api;

import com.mcdonalds.ecommerce.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ResponseStatus(value = UNAUTHORIZED, reason = UnauthorizedException.MESSAGE)
public class UnauthorizedException extends ApiException {
    public static final transient String MESSAGE = "Authorization required.";
    private static final long serialVersionUID = -8258940063007061800L;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

}