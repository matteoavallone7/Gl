package com.example.ispw.dao.queries;

import java.sql.*;

public class CRUDQueries {

    private static PreparedStatement preparedStatement = null;
    private CRUDQueries() {
    }

    public static int insertNewReview(Connection connection, int episodeID, int seriesID, int seasonID, String text, float rating, String user) throws SQLException {
        String review = "INSERT INTO review (rating, username, episode, season, series, overview) VALUES (?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(review, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setFloat(1, rating);
        preparedStatement.setString(2, user);
        preparedStatement.setInt(3, episodeID);
        preparedStatement.setInt(4, seasonID);
        preparedStatement.setInt(5, seriesID);
        preparedStatement.setString(6, text);
        return preparedStatement.executeUpdate();
    }

    public static int insertPost(Connection connection, String title, String description, String imgSrc, int tvSeries) throws SQLException {
        if (imgSrc == null) {
            String post = "INSERT INTO post (description, author, title, tv_series) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(post, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, description);
            preparedStatement.setInt(2, tvSeries);
            preparedStatement.setString(3, title);
            preparedStatement.setInt(4, tvSeries);
            return preparedStatement.executeUpdate();
        } else {
            String imgPost = "INSERT INTO post (description, author, image, title, tv_series) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(imgPost, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, description);
            preparedStatement.setInt(2, tvSeries);
            preparedStatement.setString(3, imgSrc);
            preparedStatement.setString(4, title);
            preparedStatement.setInt(5, tvSeries);
            return preparedStatement.executeUpdate();
        }
    }

    public static int insertNewSeries(Connection connection, int tvSeries, int watchlist, String status) throws SQLException {
        String series = "INSERT INTO watchlist_series VALUES (?,?,?)";
        preparedStatement = connection.prepareStatement(series, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, watchlist);
        preparedStatement.setInt(2, tvSeries);
        preparedStatement.setString(3, status);
        return preparedStatement.executeUpdate();
    }

    public static int deleteSeries(Connection connection, int tvSeries, int watchlist) throws SQLException {
        String deleted = "DELETE FROM watchlist_series WHERE watchlist = ? AND  tv_series = ?";
        preparedStatement = connection.prepareStatement(deleted, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, watchlist);
        preparedStatement.setInt(2, tvSeries);
        return preparedStatement.executeUpdate();
    }

    public static int updateEpisode(Connection connection, int watchlistID, int episodeID, int seriesID, int seasonID) throws SQLException {
        String fullyUpdate = "UPDATE watchlist_episode SET episode_status = 1 WHERE watchlist = ? AND episode = ? AND series = ? AND season = ?";
        preparedStatement = connection.prepareStatement(fullyUpdate, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, watchlistID);
        preparedStatement.setInt(2, episodeID);
        preparedStatement.setInt(3, seriesID);
        preparedStatement.setInt(4, seasonID);
        return preparedStatement.executeUpdate();
    }

    public static int updatePartialEpisode(Connection connection, int watchlistID, int episodeID, int seriesID, int seasonID, String timeslot) throws SQLException {
        String partialUpdate = "UPDATE watchlist_episode SET time_mark = ? WHERE watchlist = ? AND episode = ? AND series = ? AND season = ?";
        preparedStatement = connection.prepareStatement(partialUpdate, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, timeslot);
        preparedStatement.setInt(2, watchlistID);
        preparedStatement.setInt(3, episodeID);
        preparedStatement.setInt(4, seriesID);
        preparedStatement.setInt(5, seasonID);
        return preparedStatement.executeUpdate();
    }

    public static int updateStatus(Connection connection, int watchlistID, int seriesID, String newStatus) throws SQLException {
        String sql = "UPDATE watchlist_series SET series_status = ? WHERE watchlist = ? AND tv_series = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, newStatus);
        preparedStatement.setInt(2, watchlistID);
        preparedStatement.setInt(3, seriesID);
        return preparedStatement.executeUpdate();
    }

    public static int insertTrack(Connection connection, int episode, int season, int series, int track) throws SQLException {
        String sql = "INSERT INTO episode_track VALUES(?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, track);
        preparedStatement.setInt(2, episode);
        preparedStatement.setInt(3, season);
        preparedStatement.setInt(4, series);
        return preparedStatement.executeUpdate();
    }

    public static int insertRequest(Connection connection, String viewer, int seriesAccount, String request) throws SQLException {
        String sql = "INSERT INTO request (viewer, series_account, description) VALUES(?,?,?)";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, viewer);
        preparedStatement.setInt(2, seriesAccount);
        preparedStatement.setString(3, request);
        return preparedStatement.executeUpdate();
    }

    public static int updateRequest(Connection connection, String request) throws SQLException {
        String sql = "UPDATE request SET pending = 1 WHERE description = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, request);
        return preparedStatement.executeUpdate();
    }


}
