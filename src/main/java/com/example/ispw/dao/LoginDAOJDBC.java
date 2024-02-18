package com.example.ispw.dao;

import com.example.ispw.connection.DBConnection;
import com.example.ispw.dao.queries.SimpleQueries;
import com.example.ispw.exceptions.InvalidUserCredentialsException;
import com.example.ispw.model.UserProfile;
import com.example.ispw.utilities.Printer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginDAOJDBC implements LoginDAO {


    @Override
    public UserProfile findUser(String username, String password) {

        Connection conn;
        int role;
        UserProfile userProfile = null;

        try {

            conn = DBConnection.getConnection();
            ResultSet rs = SimpleQueries.checkUser(Objects.requireNonNull(conn), username, password);

            if (!rs.first()) {
                throw new InvalidUserCredentialsException();
            }

            rs.first();
            role = rs.getInt(1);
            userProfile = new UserProfile(username, role);
            rs.close();

        } catch (SQLException | InvalidUserCredentialsException e) {
            Printer.printError(e.getMessage());
            e.printStackTrace();
        }

        return userProfile;
    }
}

