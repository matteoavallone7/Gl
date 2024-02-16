package com.example.ispw.dao;

import com.example.ispw.exceptions.InvalidUserCredentialsException;
import com.example.ispw.exceptions.SessionUserException;
import com.example.ispw.model.UserProfile;

import java.sql.SQLException;

public interface LoginDAO {

    UserProfile findUser(String username, String password);
}
