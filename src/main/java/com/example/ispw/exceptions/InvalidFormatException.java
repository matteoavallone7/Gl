package com.example.ispw.exceptions;

public class InvalidFormatException extends Exception {

    public InvalidFormatException(){
        super("Command not found");
    }
    public InvalidFormatException(String errorMsg) {
        super(errorMsg);
    }
}
