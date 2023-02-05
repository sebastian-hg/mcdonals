package com.mcdonalds.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClientExistException extends Exception{

    private static final String MESSAGE = "Client exists";


    public ClientExistException() {
        super(MESSAGE);
    }
}

