package com.mcdonalds.ecommerce.exception.client;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DocumentIDAlreadyExistException extends Exception {
    private static final String MESSAGE = "DocumentID already exist";

    public DocumentIDAlreadyExistException() {
        super(MESSAGE);
    }
}
