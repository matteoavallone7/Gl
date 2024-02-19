package com.example.ispw.dao;

import com.example.ispw.connection.DBConnection;
import com.example.ispw.dao.queries.CRUDQueries;
import com.example.ispw.dao.queries.SimpleQueries;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.model.Review;
import com.example.ispw.utilities.Printer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    private ReviewDAO() {}

    public static List<Review> fetchReviews(int episode, int season, String series) {
        Connection connection;
        List<Review> reviews = new ArrayList<>();
        int tvSeriesID;

        try {
            connection = DBConnection.getConnection();
            tvSeriesID = SeriesDAO.getSeriesId(series);

            ResultSet resultSet = SimpleQueries.getReviews(connection, episode, season, tvSeriesID);
            if(!resultSet.first()) {
                throw new DAOException("No reviews currently available");
            }

            resultSet.first();
            while (resultSet.next()) {
                float rating = resultSet.getFloat(1);
                String username = resultSet.getString(2);
                String text = resultSet.getString(3);
                LocalDate pubDate = resultSet.getDate(4).toLocalDate();

                Review rev = new Review(username, text, rating, pubDate);
                reviews.add(rev);
            }

            resultSet.close();
        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return reviews;
    }

    public static void postReview(Review review) {
        Connection connection;
        int tvSeriesId;

        try {
            connection = DBConnection.getConnection();
            tvSeriesId = SeriesDAO.getSeriesId(review.getTvSeries());
            CRUDQueries.insertNewReview(connection, review.getEpisodeId(), tvSeriesId, review.getSeason(), review.getReviewText(), review.getRating(), review.getUsername());

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static double getAvgRating(int id, int season, int series) {
        Connection connection;
        double avgRating = 0.0;

        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.getEpisodeGeneralRating(connection, id, season, series);
            if(!resultSet.first()) {
                throw new DAOException("0.0");
            }

            resultSet.first();
            avgRating = resultSet.getDouble(1);

            resultSet.close();
        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return avgRating;
    }
}
