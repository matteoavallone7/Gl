package com.example.ispw.patterns;

import com.example.ispw.dao.LoginDAO;
import com.example.ispw.dao.LoginDAOCSV;
import com.example.ispw.dao.LoginDAOJDBC;

import java.time.LocalTime;

public class LoginDAOFactory {
    private LoginDAOFactory(){}

    private static LoginDAOFactory instance = null;

    public static LoginDAOFactory getInstance() {
        if (instance == null) {
            instance = new LoginDAOFactory();
        }
        return instance;
    }

    public LoginDAO createLoginDAO() {

        if (LocalTime.now().getMinute()%2 == 0) {
            return new LoginDAOJDBC();
        } else {
            return new LoginDAOCSV();
        }

    }

}
