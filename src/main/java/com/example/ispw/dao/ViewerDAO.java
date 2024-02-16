package com.example.ispw.dao;

import com.example.ispw.connection.DBConnection;
import com.example.ispw.dao.queries.SimpleQueries;
import com.example.ispw.exceptions.InvalidUserCredentialsException;
import com.example.ispw.model.Viewer;
import com.example.ispw.utilities.Printer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.SimpleFormatter;

public class ViewerDAO {

    public static Viewer fetchViewerByUsername(String username) throws InvalidUserCredentialsException {
        Connection connection;
        Viewer viewer = null;

        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.retrieveViewerByUsername(connection, username);

            if(!resultSet.first()) {
                throw new InvalidUserCredentialsException("No viewer found with username: " + username);
            }

            resultSet.first();
            do  {
                viewer = setViewerData(username, resultSet);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException e) {
            Printer.print(e.getMessage());
        }

        return viewer;
    }

    public static Viewer setViewerData(String username, ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("nome");
        String email = resultSet.getString("email");

        return new Viewer(username, email, name);
    }
}
