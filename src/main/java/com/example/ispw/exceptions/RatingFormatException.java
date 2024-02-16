package com.example.ispw.exceptions;

public class RatingFormatException extends Exception {

    public RatingFormatException() {
        super("The rating must be a number between 1 and 10");
    }
}
