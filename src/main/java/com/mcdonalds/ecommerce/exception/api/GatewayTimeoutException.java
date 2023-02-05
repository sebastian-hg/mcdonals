package com.mcdonalds.ecommerce.exception.api;

import com.mcdonalds.ecommerce.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;

@ResponseStatus(value = GATEWAY_TIMEOUT, reason = GatewayTimeoutException.MESSAGE)
public class GatewayTimeoutException extends ApiException {

    public static final transient String MESSAGE = "Gateway Time Out";
    private static final long serialVersionUID = 531552967790316733L;

    public GatewayTimeoutException() {
        super();
    }

    public GatewayTimeoutException(String message) {
        super(message);
    }

    public GatewayTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewayTimeoutException(Throwable cause) {
        super(cause);
    }
}