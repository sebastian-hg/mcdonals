package com.mcdonalds.ecommerce.exception.api;


import com.mcdonalds.ecommerce.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;

@ResponseStatus(value = BAD_GATEWAY, reason = BadGatewayException.MESSAGE)
public class BadGatewayException extends ApiException {
    public static final transient String MESSAGE = "Server not available";
    private static final long serialVersionUID = 444818721722319498L;

    public BadGatewayException() {
        super(MESSAGE);
    }

    public BadGatewayException(String message) {
        super(message);
    }

    public BadGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadGatewayException(Throwable cause) {
        super(cause);
    }

}