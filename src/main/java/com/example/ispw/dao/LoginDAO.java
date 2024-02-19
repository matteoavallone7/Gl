package com.example.ispw.dao;

import com.example.ispw.model.UserProfile;


public interface LoginDAO {

    UserProfile findUser(String username, String password);
}
