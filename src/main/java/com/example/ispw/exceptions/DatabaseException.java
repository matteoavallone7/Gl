package com.example.ispw.exceptions;

public class DatabaseException extends Exception {

    public DatabaseException() {
        super("Oops! Error encountered during DB connection. Try again\n");
    }
    public DatabaseException(String errMsg) {
        super(errMsg);
    }

}
