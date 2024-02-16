package com.example.ispw.dao;

import com.example.ispw.connection.DBConnection;
import com.example.ispw.dao.queries.SimpleQueries;
import com.example.ispw.exceptions.SeriesNotFoundException;
import com.example.ispw.model.Season;
import com.example.ispw.utilities.Printer;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SeasonDAO {

    private SeasonDAO() {}

    public static Season getMinSeason(int seriesId) {
        Connection connection;
        int watchlist;
        Season season = null;

        try {
            connection = DBConnection.getConnection();
            watchlist = WatchlistDAO.getWatchlistIdFromUser();
            ResultSet resultSet = SimpleQueries.getMinSeason(connection, watchlist, seriesId);
            if(!resultSet.first()) {
                throw new SeriesNotFoundException();
            }

            resultSet.first();
            int minSeason = resultSet.getInt(1);
            season = new Season(minSeason, watchlist);

            resultSet.close();
        } catch (SQLException | SeriesNotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return season;
    }
}
