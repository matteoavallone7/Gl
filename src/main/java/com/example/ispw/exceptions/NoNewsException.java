package com.example.ispw.exceptions;

public class NoNewsException extends Exception {

    public NoNewsException() {
        super("Feed is empty");
    }
}
