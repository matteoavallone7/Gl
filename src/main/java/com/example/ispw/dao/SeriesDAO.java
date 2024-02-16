package com.example.ispw.dao;

import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.connection.DBConnection;
import com.example.ispw.dao.queries.CRUDQueries;
import com.example.ispw.dao.queries.SimpleQueries;
import com.example.ispw.enums.SeriesAiringStatus;
import com.example.ispw.enums.SeriesStatus;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.exceptions.DatabaseException;
import com.example.ispw.exceptions.SeriesNotFoundException;
import com.example.ispw.model.Review;
import com.example.ispw.model.SearchModel;
import com.example.ispw.model.TvSeries;
import com.example.ispw.session.Session;
import com.example.ispw.utilities.Printer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeriesDAO {

    private SeriesDAO() {
    }

    public static int getSeriesId(String seriesName) {
        Connection connection;
        int seriesId = 0;

        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.getSeriesIDFromName(connection, seriesName);
            if (!resultSet.first()) {
                throw new SeriesNotFoundException();
            }


            do {
                seriesId = resultSet.getInt(1);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException | SeriesNotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return seriesId;
    }

    public static void addSeries(TvSeries tvSeries, String status) {
        Connection connection;
        int watchlist;

        try {

            watchlist = WatchlistDAO.getWatchlistIdFromUser();
            connection = DBConnection.getConnection();
            CRUDQueries.insertNewSeries(connection, getSeriesId(tvSeries.getName()), watchlist, status);

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static void deleteSeries(TvSeries tvSeries) {
        Connection connection;
        int watchlist;
        int seriesId;

        try {
            connection = DBConnection.getConnection();
            seriesId = getSeriesId(tvSeries.getName());
            System.out.println(seriesId);
            watchlist = WatchlistDAO.getWatchlistIdFromUser();
            CRUDQueries.deleteSeries(connection, seriesId, watchlist);

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static List<TvSeries> getSearchResults(SearchModel searchModel) throws SeriesNotFoundException {
        Connection connection;
        List<TvSeries> tvSeriesList = new ArrayList<>();
        int seriesId;

        try {
            connection = DBConnection.getConnection();
            seriesId = getSeriesId(searchModel.getName());
            ResultSet resultSet = SimpleQueries.getSeriesResults(connection, searchModel.getName());
            if (!resultSet.first()) {
                throw new SeriesNotFoundException();
            }

            resultSet.first();
            do {
                String name = resultSet.getString(1);
                String image = resultSet.getString(2);
                String genre = resultSet.getString(3);
                float rating = getSeriesRating(seriesId);
                TvSeries tvSeries = new TvSeries(name, image, genre, rating);
                tvSeriesList.add(tvSeries);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return tvSeriesList;
    }

    public static TvSeries getTvSeriesDetails(String tvSeriesName) {
        Connection connection;
        TvSeries tvSeries = null;
        int seriesId;

        try {
            connection = DBConnection.getConnection();
            seriesId = getSeriesId(tvSeriesName);
            ResultSet rs = SimpleQueries.getSeriesDetails(connection, tvSeriesName);
            if (!rs.first()) {
                throw new DAOException("Details not available for this tv show");
            }

            rs.first();
            do {
                String name = rs.getString(1);
                int numSeasons = rs.getInt(2);
                String plot = rs.getString(3);
                String image = rs.getString(4);
                String country = rs.getString(5);
                String genre = rs.getString(6);
                LocalDate airingDate = rs.getDate(7).toLocalDate();
                SeriesAiringStatus airingStatus = SeriesAiringStatus.fromString(rs.getString(8));
                int numEpisodes = rs.getInt(9);
                float rating = getSeriesRating(seriesId);
                tvSeries = new TvSeries(name, numSeasons, plot, numEpisodes, genre, image, country, airingDate, airingStatus, rating);
            } while (rs.next());

            rs.close();
        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return tvSeries;
    }

    public static TvSeries getTvSeriesDet(String tvSeriesName) {
        Connection connection;
        TvSeries tvSeries = null;

        try {
            connection = DBConnection.getConnection();
            ResultSet rs = SimpleQueries.getSeriesDet(connection, tvSeriesName);
            if (!rs.first()) {
                throw new DAOException("Details not available for this tv show");
            }

            rs.first();
            do {
                String name = rs.getString(1);
                String image = rs.getString(2);
                LocalDate airingDate = rs.getDate(3).toLocalDate();
                tvSeries = new TvSeries(name, image, airingDate);
            } while (rs.next());

            rs.close();
        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return tvSeries;
    }

    public static String getSeriesName(int seriesId) {
        Connection connection;
        String seriesName = null;

        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.getSeriesNameFromID(connection, seriesId);
            if (!resultSet.first()) {
                throw new SeriesNotFoundException();
            }

            resultSet.first();
            seriesName = resultSet.getString(1);

            resultSet.close();
        } catch (SQLException | SeriesNotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return seriesName;
    }
    
    public static float getSeriesRating(int seriesID) {
        Connection connection;
        float rating = 0;
        
        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.getSeriesGeneralRating(connection, seriesID);
            if (!resultSet.first()) {
                throw new DAOException("Not enough info");
            }

            resultSet.first();
            rating = resultSet.getFloat(1);
            
            resultSet.close();
        } catch (SQLException | DAOException e) {
            Printer.print(e.getMessage());
        }
        
        return rating;
    }

    public static List<TvSeries> randomSeries() {
        Connection connection;
        List<TvSeries> tvSeriesList = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.getRandomSeries(connection);
            if (!resultSet.first()) {
                throw new DAOException("Not enough info");
            }

            resultSet.first();
            do {
                String name = resultSet.getString(1);
                String genre = resultSet.getString(2);
                LocalDate airingDate = resultSet.getDate(3).toLocalDate();
                String image = resultSet.getString(4);
                TvSeries tvSeries = new TvSeries(name, genre, airingDate, image);
                tvSeriesList.add(tvSeries);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return tvSeriesList;
    }

    public static LocalDate getSeriesDate(String seriesName) {
        Connection connection;
        LocalDate status = null;

        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.getSeriesStatus(connection, seriesName);
            if (!resultSet.first()) {
                throw new DAOException("Unable to retrieve series airing date");
            }

            resultSet.first();
            status = resultSet.getDate(1).toLocalDate();

            resultSet.close();
        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return status;
    }

}
