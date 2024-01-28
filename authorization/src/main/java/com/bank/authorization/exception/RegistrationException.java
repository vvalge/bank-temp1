package com.bank.authorization.exception;

public class RegistrationException extends Exception {
    private String message;

    public RegistrationException(String message) {
        this.message = message;
    }

}
