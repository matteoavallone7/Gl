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
            if (conn == null) {
                System.out.println("Connection is null. Unable to establish a connection.");
                // Handle the situation accordingly (e.g., throw an exception, return, log an error)
            } else {
                System.out.println("Connection established successfully.");
                // Continue with database operations using the connection
            }
            ResultSet rs = SimpleQueries.checkUser(Objects.requireNonNull(conn), username, password);

            if (!rs.first()) {
                throw new InvalidUserCredentialsException();
            }

            rs.first();
            role = rs.getInt(1);
            System.out.println("Role: " + role);
            userProfile = new UserProfile(username, role);
            System.out.println("UserProfile created: " + userProfile);
            rs.close();

        } catch (SQLException | InvalidUserCredentialsException e) {
            Printer.printError(e.getMessage());
            e.printStackTrace();
        }

        return userProfile;
    }
}

