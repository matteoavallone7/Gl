package com.example.ispw.dao;

import com.example.ispw.bean.EpisodeBean;
import com.example.ispw.bean.ReviewBean;
import com.example.ispw.connection.DBConnection;
import com.example.ispw.dao.queries.CRUDQueries;
import com.example.ispw.dao.queries.SimpleQueries;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.exceptions.RatingErrorException;
import com.example.ispw.model.*;
import com.example.ispw.utilities.DateSupport;
import com.example.ispw.utilities.Printer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class EpisodeDAO {

    private EpisodeDAO() {}

    public static void updateFullEp(int id, int season, String seriesName) {
        Connection connection;
        int watchlist;
        int series;

        try {
            connection = DBConnection.getConnection();
            watchlist = WatchlistDAO.getWatchlistIdFromUser();
            series = SeriesDAO.getSeriesId(seriesName);
            CRUDQueries.updateEpisode(connection, watchlist, id, series, season);

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static void updatePartialEp(PartiallyWatchedEp partiallyWatchedEp) {
        Connection connection;
        int watchlist;
        int series;

        try {
            connection = DBConnection.getConnection();
            watchlist = WatchlistDAO.getWatchlistIdFromUser();
            series = SeriesDAO.getSeriesId(partiallyWatchedEp.getTvSeries());
            CRUDQueries.updatePartialEpisode(connection, watchlist, partiallyWatchedEp.getId(), series, partiallyWatchedEp.getSeason(), partiallyWatchedEp.getTimeslot());

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static List<Track> getEpTracks(int id, int season, String seriesName) {
        Connection connection;
        List<Track> trackList = new ArrayList<>();
        int series;

        try {
            connection = DBConnection.getConnection();
            series = SeriesDAO.getSeriesId(seriesName);
            ResultSet resultSet = SimpleQueries.getTracks(connection, id, season, series);
            if(!resultSet.first()) {
                throw new DAOException("This episode contains no tracks");
            }

            resultSet.first();
            do {
                String author = resultSet.getString(1);
                String title = resultSet.getString(2);
                String image = resultSet.getString(3);
                Track track = new Track(author, title, image);
                trackList.add(track);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return trackList;
    }

    public static Episode getEpDetails(int id, int season, String seriesName) {
        Connection connection;
        Episode episode = null;

        try {
            connection = DBConnection.getConnection();
            int series = SeriesDAO.getSeriesId(seriesName);
            double averageRating = ReviewDAO.getAvgRating(id, season, series);
            ResultSet resultSet = SimpleQueries.getEpDetails(connection, id, season, series);
            if(!resultSet.first()) {
                throw new DAOException("Details not available for this episode");
            }

            resultSet.first();
            do {
                String overview = resultSet.getString(1);
                String title = resultSet.getString(2);
                String image = resultSet.getString(3);
                int seasonId = resultSet.getInt(4);
                String runningTime = resultSet.getString(5);
                LocalDate airingDate = resultSet.getDate(6).toLocalDate();
                episode = new Episode(id, overview, title, image, seasonId, seriesName, averageRating, runningTime, airingDate);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return episode;
    }

    public static Episode getLatestEpisode(String series) {
        Connection connection;
        Episode episode = null;
        Season season;

        try {
            connection = DBConnection.getConnection();
            int seriesId = SeriesDAO.getSeriesId(series);
            season = SeasonDAO.getMinSeason(seriesId);
            ResultSet resultSet = SimpleQueries.getLatestUnwatchedEp(connection, season.getWatchlistId(), season.getSeasonNum(), seriesId);
            if(!resultSet.first()) {
                throw new DAOException("Something went wrong");
            }

            resultSet.first();
            do {
                episode = new Episode(resultSet.getInt(1), season.getSeasonNum(), resultSet.getDate(2).toLocalDate(), resultSet.getString(3));
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return episode;
    }

    public static String getEpisodeRunningTime(int episode, int season, String seriesName) {
        Connection connection;
        int series;
        String runningTime = null;

        try {
            connection = DBConnection.getConnection();
            series = SeriesDAO.getSeriesId(seriesName);
            ResultSet resultSet = SimpleQueries.getEpRunningTime(connection, episode, season, series);
            if (!resultSet.first()) {
                throw new DAOException("Unable to retrieve episode running time");
            }

            resultSet.first();
            runningTime = resultSet.getString(1);

            resultSet.close();
        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return runningTime;
    }

    public static int verifyEpisode(int episode, int season, String seriesName) {
        Connection connection;
        int series;
        int verification = 0;

        try {
            connection = DBConnection.getConnection();
            series = SeriesDAO.getSeriesId(seriesName);
            ResultSet resultSet = SimpleQueries.getEpTrackInfo(connection, episode, season, series);

            if (!resultSet.isBeforeFirst() && resultSet.getRow() == 0) { // true if rs is empty, false if not
                verification = 0;
            } else {
                verification = 1;
            }

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return verification;
    }

    public static void addTrack(int episode, int season, String author, String title, String seriesName) {
        Connection connection;
        int series;
        int trackID;

        try {
            connection = DBConnection.getConnection();
            series = SeriesDAO.getSeriesId(seriesName);
            trackID = getTrackNumber(author, title);
            CRUDQueries.insertTrack(connection, episode, season, series, trackID);

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static int getTrackNumber(String author, String title) {
        Connection connection;
        int trackID = 0;

        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.getTrackID(connection, author, title);
            if (!resultSet.first()) {
                throw new DAOException("No track found");
            }

            resultSet.first();
            trackID = resultSet.getInt(1);

            resultSet.close();
        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return trackID;
    }

    public static List<Track> getSearchResults(SearchModel searchModel) throws DAOException {
        Connection connection;
        List<Track> trackList = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.getTrackResults(connection, searchModel.getName());
            if (!resultSet.first()) {
                throw new DAOException("0 results found");
            }

            resultSet.first();
            do {
                String author = resultSet.getString(1);
                String title = resultSet.getString(2);
                String image = resultSet.getString(3);
                Track track = new Track(author, title, image);
                trackList.add(track);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return trackList;
    }
}
