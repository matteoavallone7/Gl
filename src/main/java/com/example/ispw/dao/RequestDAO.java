package com.example.ispw.dao;

import com.example.ispw.bean.RequestBean;
import com.example.ispw.connection.DBConnection;
import com.example.ispw.dao.queries.CRUDQueries;
import com.example.ispw.dao.queries.SimpleQueries;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.model.Request;
import com.example.ispw.model.Review;
import com.example.ispw.utilities.Printer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {

    private RequestDAO() {}

    public static void makeRequest(Request request, String viewer) {
        Connection connection;
        int seriesAccount;

        try {
            connection = DBConnection.getConnection();
            seriesAccount = SeriesOffAccountDAO.getVerificationIdFromName(request.getSeries());
            CRUDQueries.insertRequest(connection, viewer, seriesAccount, request.getDescription());

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static List<Request> getUserRequest(String series) {
        Connection connection;
        int seriesAccount;
        List<Request> requests = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();
            seriesAccount = SeriesOffAccountDAO.getVerificationIdFromName(series);
            ResultSet resultSet = SimpleQueries.getLatestRequests(connection, seriesAccount);
            if(!resultSet.first()) {
                throw new DAOException("No requests found");
            }

            resultSet.first();
            do {
                String requestText = resultSet.getString(1);
                int frequency = resultSet.getInt(2);
                Request request = new Request(frequency, requestText);
                requests.add(request);
            } while (resultSet.next());

        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return requests;
    }

    public static void changeStatus(Request request) {
        Connection connection;

        try {
            connection = DBConnection.getConnection();
            CRUDQueries.updateRequest(connection, request.getDescription());

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }
}
