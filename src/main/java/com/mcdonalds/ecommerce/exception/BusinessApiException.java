package com.mcdonalds.ecommerce.exception;

import com.mcdonalds.ecommerce.handlerErrors.response.ErrorResponse;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * es para manejar reglas de negocio
 */
public class BusinessApiException extends BaseException {

    private static final long serialVersionUId = 1L;
    private int statusCode = 209;
    private String businessErrorCode = "BE_209";
    private transient List<ObjectError> errorList;

    public BusinessApiException() {
    }

    public BusinessApiException(String message) {
        super(message);
    }

    public BusinessApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessApiException(Throwable cause) {
        super(cause);
    }

    public BusinessApiException(String businessErrorCode, String message) {
        super(message);
        this.businessErrorCode = businessErrorCode;
    }

    public BusinessApiException(String businessErrorCode, int statusCode, String message) {
        this(businessErrorCode, message);
        this.statusCode = statusCode;
    }

    public BusinessApiException(String businessErrorCode, String message, List<ObjectError> details) {
        super(message);
        this.businessErrorCode = businessErrorCode;
        this.errorList = details;
    }

    public BusinessApiException(String businessErrorCode, int statusCode, String message, List<ObjectError> details) {
        this(businessErrorCode, message, details);
        this.statusCode = statusCode;
    }

    public BusinessApiException(String businessErrorCode, String message, Throwable cause) {
        super(message, cause);
        this.businessErrorCode = businessErrorCode;
    }

    public BusinessApiException(String businessErrorCode, int statusCode, String message, Throwable cause) {
        this(businessErrorCode, message, cause);
        this.statusCode = statusCode;
    }

    public BusinessApiException(String businessErrorCode, int statusCode, Throwable cause) {
        this(businessErrorCode, cause);
        this.statusCode = statusCode;
    }

    public BusinessApiException(String businessErrorCode, int statusCode, String message, Errors errors) {
        this(businessErrorCode, statusCode, message);
        this.errorList = errors.getAllErrors();
    }

    public String getBusinessErrorCode() {
        return businessErrorCode;
    }

    public List<ObjectError> getErrorList() {
        return errorList;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public Mono<ServerResponse> handlerException() {
        int status = getStatusCode();
        String error = getErrorList() != null ? convertErrorToString(getErrorList()) : null;
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(getBusinessErrorCode())
                .errorDetail(getMessage(this))
                .httpStatusCode(status)
                .exceptionReason(error)
                .build();
        return makeServerResponse(errorResponse);
    }
    private String convertErrorToString(List<ObjectError> errors) {
        StringBuilder result = new StringBuilder();
        errors.forEach(error -> {
                    String errorMessage = error.getDefaultMessage();
                    result.append(errorMessage).append("/");
                }
        );
        return result.substring(0, result.length() - 1);
    }
    //TODO: Stringbuffer, StrinBbuilder diferencias , como crear anaotacion en java , tipos de joinpoint aspect, ejemplos
    //TODO : aplicar aspect a un metodo especifico // arraylist, map, arrays LEER
}