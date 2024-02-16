package com.example.ispw.model;

public class UserProfile {

    private String username;
    private int role;

    public UserProfile(String username, int role) {
        this.username = username;
        this.role = role;
    }

    public UserProfile() {

    }

    public String getUsername() {
        return username;
    }

    public int getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
