package com.example.ispw.exceptions;

public class SeriesNotFoundException extends Exception {

    public SeriesNotFoundException() {
        super("You may have misspelled the tv series name, please try again");
    }
}
