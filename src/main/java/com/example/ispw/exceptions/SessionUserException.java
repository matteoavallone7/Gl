package com.example.ispw.exceptions;

public class SessionUserException extends Exception {

    public SessionUserException() {
        super("User yet to be defined");
    }

    public SessionUserException(String errorMsg) {
        super(errorMsg);
    }

}
