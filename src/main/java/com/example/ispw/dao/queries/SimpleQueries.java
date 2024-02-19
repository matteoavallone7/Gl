package com.example.ispw.dao.queries;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleQueries {

    private SimpleQueries() {}
    private static PreparedStatement preparedStatement = null;


    public static ResultSet checkUser(Connection connection, String username, String password) throws SQLException {
        String sql = "SELECT role FROM user WHERE username = ? AND password = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getFeedByUser(Connection connection, int watchlistID) throws SQLException {
        String sql = "SELECT description, author, image, title, publication_date FROM post " +
                     "JOIN watchlist_series ws ON post.tv_series = ws.tv_series WHERE watchlist = ? " +
                     "ORDER BY publication_date DESC";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, watchlistID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getWatchlistIDFromUser(Connection connection, String user) throws SQLException {
        String sql = "SELECT watchlist FROM user_watchlist WHERE user = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, user);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getReviews(Connection connection, int episodeID, int seasonID, int seriesID) throws SQLException {
        String sql = "SELECT rating, username, overview, pub_date FROM review WHERE episode = ? AND season = ? AND series = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, episodeID);
        preparedStatement.setInt(2, seasonID);
        preparedStatement.setInt(3, seriesID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getSeriesResults(Connection connection, String tvSeries) throws SQLException {
        String sql = "SELECT name, image, genre FROM tv_series WHERE name = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, tvSeries);
        return preparedStatement.executeQuery();
    }

    public static ResultSet fetchWatchlistSeries(Connection connection, int watchlistID) throws SQLException {
        String sql = "SELECT name, image, series_status, airing_date FROM tv_series " +
                     "JOIN watchlist_series ON watchlist_series.tv_series = tv_series.id " +
                     "WHERE watchlist = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, watchlistID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getSeriesDet(Connection connection, String series) throws SQLException {
        String sql = "SELECT name, image, airing_date FROM tv_series WHERE name = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, series);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getSeriesDetails(Connection connection, String tvSeries) throws SQLException {
        String sql = "SELECT name, count(season.id), tv_series.overview, tv_series.image, country_origin, genre, tv_series.airing_date, status, count(episode.id)" +
                     "FROM tv_series JOIN season ON tv_series.id = season.tv_series JOIN episode ON episode.series" +
                     "= tv_series.id AND episode.season = season.id WHERE tv_series.name = ? GROUP BY tv_series.id";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, tvSeries);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getEpDetails(Connection connection, int epidoseID, int seasonID, int seriesID) throws SQLException {
        String sql = "SELECT overview, title, image, season, running_time, airing_date FROM episode" +
                     " WHERE id = ? AND season = ? AND series = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, epidoseID);
        preparedStatement.setInt(2, seasonID);
        preparedStatement.setInt(3, seriesID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getMinSeason(Connection connection, int watchlistID, int seriesID) throws SQLException {
        String sql = "SELECT MIN(season) FROM watchlist_episode WHERE watchlist = ? AND series = ? AND episode_status = 0";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, watchlistID);
        preparedStatement.setInt(2, seriesID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getLatestUnwatchedEp(Connection connection, int watchlistID, int seasonID, int seriesID) throws SQLException {
        String sql = "SELECT MIN(episode), airing_date, time_mark FROM watchlist_episode we JOIN episode ep ON we.episode = ep.id " +
                "AND we.season = ep.season AND we.series = ep.series WHERE watchlist = ? AND we.season = ? " +
                "AND we.series = ? AND episode_status = 0 GROUP BY episode";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, watchlistID);
        preparedStatement.setInt(2, seasonID);
        preparedStatement.setInt(3, seriesID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getTracks(Connection connection, int episodeID, int seasonID, int seriesID) throws SQLException {
        String sql = "SELECT author, title, image FROM episode_track JOIN track ON episode_track.track = track.id" +
                     " WHERE episode = ? AND series = ? AND season = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, episodeID);
        preparedStatement.setInt(2, seriesID);
        preparedStatement.setInt(3, seasonID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getSeriesIDFromName(Connection connection, String tvSeriesName) throws SQLException {
        String sql = "SELECT id FROM tv_series WHERE name = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, tvSeriesName);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getEpisodeGeneralRating(Connection connection, int episodeID, int seasonID, int seriesID) throws SQLException {
        String sql = "SELECT AVG(rating) FROM review WHERE episode = ? AND season = ? AND series = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, episodeID);
        preparedStatement.setInt(2, seasonID);
        preparedStatement.setInt(3, seriesID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getSeriesGeneralRating(Connection connection, int seriesID) throws SQLException {
        String sql = "SELECT AVG(rating) FROM review WHERE series = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, seriesID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getSeriesNameFromID(Connection connection, int id) throws SQLException {
        String sql = "SELECT name FROM tv_series WHERE id = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveViewerByUsername(Connection connection, String username) throws SQLException {
        String sql = "SELECT * FROM viewer WHERE username = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, username);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveSeriesAccountByUsername(Connection connection, String username) throws SQLException {
        String sql = "SELECT * FROM series_account WHERE username = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, username);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getEpRunningTime(Connection connection, int episode, int season, int series) throws SQLException {
        String sql = "SELECT running_time FROM episode WHERE id = ? AND season = ? AND series = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, episode);
        preparedStatement.setInt(2, season);
        preparedStatement.setInt(3, series);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getEpTrackInfo(Connection connection, int episode, int season, int series) throws SQLException {
        String sql = "SELECT * FROM episode WHERE id = ? AND season = ? AND series = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, episode);
        preparedStatement.setInt(2, season);
        preparedStatement.setInt(3, series);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getTrackID(Connection connection, String author, String title) throws SQLException {
        String sql = "SELECT id FROM track WHERE author = ? AND title = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, author);
        preparedStatement.setString(2, title);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveVerificationIDFromName(Connection connection, String seriesName) throws SQLException {
        String sql = "SELECT verification_id FROM series_account WHERE series_name = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, seriesName);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getLatestRequests(Connection connection, int seriesAccount) throws SQLException {
        String sql = "SELECT description, COUNT(*) FROM request WHERE series_account = ? AND pending = 0 GROUP BY description";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, seriesAccount);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getFirstNews(Connection connection, int watchlist) throws SQLException {
        String sql = "SELECT description, author, image, title, publication_date FROM post " +
                "JOIN watchlist_series ws ON post.tv_series = ws.tv_series WHERE watchlist = ? " +
                "ORDER BY publication_date DESC LIMIT 1";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, watchlist);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getRandomSeries(Connection connection) throws SQLException {
        String sql = "SELECT name, genre, airing_date, image FROM tv_series WHERE airing_date > NOW() LIMIT 3";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getTrackResults(Connection connection, String track) throws SQLException {
        String sql = "SELECT author, title, image FROM track WHERE title = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, track);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getAuthorById(Connection connection, int id) throws SQLException {
        String sql = "SELECT series_name FROM series_account WHERE verification_id = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getSeriesStatus(Connection connection, String series) throws SQLException {
        String sql = "SELECT airing_date FROM tv_series where name = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, series);
        return preparedStatement.executeQuery();
    }
}

