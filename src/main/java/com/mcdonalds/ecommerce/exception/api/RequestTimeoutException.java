package com.mcdonalds.ecommerce.exception.api;

import com.mcdonalds.ecommerce.exception.ApiException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.REQUEST_TIMEOUT;

@ResponseStatus(value = REQUEST_TIMEOUT, reason = RequestTimeoutException.MESSAGE)
public class RequestTimeoutException extends ApiException {
    public static final transient String MESSAGE = "Request Time Out";
    private static final long serialVersionUID = -3938270529523879470L;

    public RequestTimeoutException() {
        super();
    }

    public RequestTimeoutException(String message) {
        super(message);
    }

    public RequestTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestTimeoutException(Throwable cause) {
        super(cause);
    }
}