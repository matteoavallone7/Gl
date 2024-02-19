package com.example.ispw.dao;

import com.example.ispw.connection.DBConnection;
import com.example.ispw.dao.queries.SimpleQueries;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.exceptions.InvalidUserCredentialsException;
import com.example.ispw.model.SeriesOffAccount;
import com.example.ispw.utilities.Printer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeriesOffAccountDAO {

    private SeriesOffAccountDAO() {}

    public static SeriesOffAccount fetchSeriesAccountByUsername(String username) {
        Connection connection;
        SeriesOffAccount seriesOffAccount = null;

        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.retrieveSeriesAccountByUsername(connection, username);

            if(!resultSet.first()) {
                throw new InvalidUserCredentialsException("No tv series account found with username: " + username);
            }

            resultSet.first();
            do {
                seriesOffAccount = setAccountData(username, resultSet);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException | InvalidUserCredentialsException e) {
            Printer.printError(e.getMessage());
        }

        return seriesOffAccount;
    }

    public static int getVerificationIdFromName(String series) {
        Connection connection;
        int verificationID = 0;

        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.retrieveVerificationIDFromName(connection, series);
            if(!resultSet.first()) {
                throw new DAOException("No account found");
            }

            resultSet.first();
            do {
                verificationID = resultSet.getInt(1);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return verificationID;
    }

    public static SeriesOffAccount setAccountData(String username, ResultSet resultSet) throws SQLException {
        int verificationID = resultSet.getInt("verification_id");
        String name = resultSet.getString("series_name");
        String email = resultSet.getString("email");
        return new SeriesOffAccount(username, email, verificationID, name);
    }
}
