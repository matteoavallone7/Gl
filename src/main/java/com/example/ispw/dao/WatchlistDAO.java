package com.example.ispw.dao;

import com.example.ispw.connection.DBConnection;
import com.example.ispw.dao.queries.CRUDQueries;
import com.example.ispw.dao.queries.SimpleQueries;
import com.example.ispw.enums.SeriesAiringStatus;
import com.example.ispw.enums.SeriesStatus;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.exceptions.DatabaseException;
import com.example.ispw.model.TvSeries;
import com.example.ispw.session.Session;
import com.example.ispw.utilities.Printer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WatchlistDAO {

    private WatchlistDAO() {}


    public static int getWatchlistIdFromUser() {

        Connection connection;

        int wl = 0;
        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.getWatchlistIDFromUser(connection, Session.getCurrentSession().getViewerBean().getUsername());
            if (!resultSet.first()) {
                throw new DatabaseException("No watchlist is associated to the user");
            }

            resultSet.first();
            wl = resultSet.getInt(1);

            resultSet.close();
        } catch (SQLException | DatabaseException e) {
            Printer.printError(e.getMessage());
        }

        return wl;

    }

    public static List<TvSeries> getWatchlistSeries() throws DAOException {
        Connection connection;
        int watchlist;
        List<TvSeries> tvSeriesList = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();
            watchlist = getWatchlistIdFromUser();
            ResultSet resultSet = SimpleQueries.fetchWatchlistSeries(connection, watchlist);
            if(!resultSet.first()) {
                throw new DAOException("Watchlist is empty");
            }

            resultSet.first();
            do {
                String name = resultSet.getString(1);
                String image = resultSet.getString(2);
                SeriesStatus status = SeriesStatus.fromString(resultSet.getString(3));
                LocalDate airingDate = resultSet.getDate(4).toLocalDate();
                TvSeries tvSeries = new TvSeries(name, image, status, airingDate);
                tvSeriesList.add(tvSeries);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return tvSeriesList;
    }

    public static void updateStatus(String seriesName, SeriesStatus seriesStatus) {
        Connection connection;
        int watchlist;
        int series;

        try {
            connection = DBConnection.getConnection();
            watchlist = getWatchlistIdFromUser();
            series = SeriesDAO.getSeriesId(seriesName);
            CRUDQueries.updateStatus(connection, watchlist, series, seriesStatus.getId());

        } catch (SQLException e) {
            Printer.print(e.getMessage());
        }
    }
}
