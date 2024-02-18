package com.example.ispw.controller;

import com.example.ispw.bean.SearchBean;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.dao.SeriesDAO;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.exceptions.DatabaseException;
import com.example.ispw.exceptions.SeriesNotFoundException;
import com.example.ispw.model.SearchModel;
import com.example.ispw.model.TvSeries;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrowseSeriesController {

    public List<TvSeriesBean> searchSeries(SearchBean searchBean) throws SeriesNotFoundException {

        SearchModel searchModel = new SearchModel();
        searchModel.setName(searchBean.getName());
        List<TvSeriesBean> tvSeriesBeanList = new ArrayList<>();
        List<TvSeries> tvSeriesList;

        tvSeriesList = SeriesDAO.getSearchResults(searchModel);
        for (TvSeries tvSeries : tvSeriesList) {
            TvSeriesBean tvSeriesBean = new TvSeriesBean.Builder(tvSeries.getName(), tvSeries.getSeasons(), tvSeries.getImgSource(), tvSeries.getPlot(), tvSeries.getEpisodes(), tvSeries.getGenre(), tvSeries.getCountryOfOrigin()).rating(tvSeries.getRating()).build();
            tvSeriesBeanList.add(tvSeriesBean);
        }

        return tvSeriesBeanList;

    }

    public TvSeriesBean expandedDetails(String showName) {

        TvSeries tvSeries = SeriesDAO.getTvSeriesDetails(showName);
        TvSeriesBean tvSeriesBean = new TvSeriesBean.Builder(tvSeries.getName(), tvSeries.getSeasons(), tvSeries.getImgSource(), tvSeries.getPlot(), tvSeries.getEpisodes(), tvSeries.getGenre(), tvSeries.getCountryOfOrigin()).rating(tvSeries.getRating()).airingDate(tvSeries.getAiringDate()).watchlistStatus(tvSeries.getAiringStatus()).build();
        return tvSeriesBean;
    }
}
