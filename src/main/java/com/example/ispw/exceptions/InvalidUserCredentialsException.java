package com.example.ispw.exceptions;

public class InvalidUserCredentialsException extends Exception {

    public InvalidUserCredentialsException() {
        super("User not found");
    }
    public InvalidUserCredentialsException(String err) {
        super(err);
    }
}
