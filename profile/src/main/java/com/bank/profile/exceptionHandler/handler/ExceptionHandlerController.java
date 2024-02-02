package com.bank.profile.exceptionHandler.handler;


import com.bank.profile.exceptionHandler.exception.NotFoundExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundExceptionEntity.class)
    public ResponseEntity<String> handleNotFoundExceptionEntity(NotFoundExceptionEntity ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
