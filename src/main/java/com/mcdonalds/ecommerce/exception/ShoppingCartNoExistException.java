package com.mcdonalds.ecommerce.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ShoppingCartNoExistException extends Exception {
    private static final String MESSAGE = "shopping cart not exist";


    public ShoppingCartNoExistException() {
        super(MESSAGE);
    }
}
