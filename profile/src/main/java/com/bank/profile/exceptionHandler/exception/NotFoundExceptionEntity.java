package com.bank.profile.exceptionHandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptionEntity extends RuntimeException{
    public NotFoundExceptionEntity(String message) {
        super(message);
    }
}
