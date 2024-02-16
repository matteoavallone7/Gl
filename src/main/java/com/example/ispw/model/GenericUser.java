package com.example.ispw.model;

public abstract class GenericUser {
    private String username;
    private String email;

    protected GenericUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

    protected GenericUser(){}

    public String getUsername() {
        return username;
    }


    public String getEmail() {
        return email;
    }

}
