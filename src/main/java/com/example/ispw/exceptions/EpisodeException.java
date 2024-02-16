package com.example.ispw.exceptions;

public class EpisodeException extends Exception {

    public EpisodeException() {
        super("There seems to be an error with \n either the nº of episode or the season");
    }
}
