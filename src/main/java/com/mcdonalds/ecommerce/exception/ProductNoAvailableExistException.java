package com.mcdonalds.ecommerce.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNoAvailableExistException extends Exception {
    private static final String MESSAGE = "product not available";


    public ProductNoAvailableExistException() {
        super(MESSAGE);
    }
}
