package com.example.ispw.model;

public class Viewer extends GenericUser {

    private String name;

    public Viewer(String username, String email, String name) {
        super(username, email);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
