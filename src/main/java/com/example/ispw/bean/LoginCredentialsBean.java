package com.example.ispw.bean;

import com.example.ispw.exceptions.InvalidFormatException;
import com.example.ispw.exceptions.InvalidUserCredentialsException;
import com.example.ispw.utilities.Printer;

public class LoginCredentialsBean {

    private String username;
    private String password;
    private int role; // 1 for 'Viewer', 2 for 'Tv Series Official Account'

    public LoginCredentialsBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
