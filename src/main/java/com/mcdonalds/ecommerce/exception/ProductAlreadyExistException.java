package com.mcdonalds.ecommerce.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductAlreadyExistException extends Exception {
    private static final String MESSAGE = "Product already exist";

    public ProductAlreadyExistException() {
        super(MESSAGE);
    }
}
