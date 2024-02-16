package com.example.ispw.exceptions;

public class EmptyFieldException extends Exception {

    public EmptyFieldException() {
        super("Fields are empty");
    }
}
