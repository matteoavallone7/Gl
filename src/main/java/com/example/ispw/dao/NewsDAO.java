package com.example.ispw.dao;

import com.example.ispw.connection.DBConnection;
import com.example.ispw.dao.queries.CRUDQueries;
import com.example.ispw.dao.queries.SimpleQueries;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.exceptions.NoNewsException;
import com.example.ispw.model.Post;
import com.example.ispw.session.Session;
import com.example.ispw.utilities.Printer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {

    private NewsDAO() {}

    public static List<Post> findLatestNews() throws NoNewsException {

        Connection connection;
        int watchlist;
        List<Post> postList = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();
            watchlist = WatchlistDAO.getWatchlistIdFromUser();

            ResultSet resultSet = SimpleQueries.getFeedByUser(connection, watchlist);
            if (!resultSet.first()) {
                throw new NoNewsException();
            }

            resultSet.first();
            do {
                String description = resultSet.getString(1);
                String author = getAuthorName(resultSet.getInt(2));
                String image = resultSet.getString(3);
                String title = resultSet.getString(4);
                LocalDate publicationDate = resultSet.getDate(5).toLocalDate();

                Post post = new Post(description, author, title, image, publicationDate);
                postList.add(post);
            } while (resultSet.next());

            resultSet.close();
        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        return postList;

    }

    public static void postUpdate(Post post) {

        Connection connection;
        int tvSeries;

        try {
            connection = DBConnection.getConnection();
            tvSeries = SeriesDAO.getSeriesId(Session.getCurrentSession().getSeriesOffAccountBean().getSeriesName());
            CRUDQueries.insertPost(connection, post.getTitle(), post.getDescription(), post.getImageSource(), tvSeries);

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }
    }

    public static Post getNewsOverview() {

        Connection connection;
        int watchlist;

        Post post = null;
        try {
            connection = DBConnection.getConnection();
            watchlist = WatchlistDAO.getWatchlistIdFromUser();
            ResultSet resultSet = SimpleQueries.getFirstNews(connection, watchlist);
            if (!resultSet.first()) {
                throw new NoNewsException();
            }

            resultSet.first();
            do {
                String description = resultSet.getString(1);
                String author = getAuthorName(resultSet.getInt(2));
                String image = resultSet.getString(3);
                String title = resultSet.getString(4);
                LocalDate publicationDate = resultSet.getDate(5).toLocalDate();

                post = new Post(description, author, title, image, publicationDate);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | NoNewsException e) {
            Printer.printError(e.getMessage());
        }

        return post;
    }

    public static String getAuthorName(int verificationId) {
        Connection connection;
        String author = null;

        try {
            connection = DBConnection.getConnection();
            ResultSet resultSet = SimpleQueries.getAuthorById(connection, verificationId);
            if (!resultSet.first()) {
                throw new DAOException("No such author");
            }

            resultSet.first();
            do {
                author = resultSet.getString(1);
            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | DAOException e) {
            Printer.printError(e.getMessage());
        }

        return author;
    }
}
