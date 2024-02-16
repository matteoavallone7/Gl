package com.example.ispw.controller;

import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.dao.SeriesDAO;
import com.example.ispw.model.TvSeries;
import com.example.ispw.patterns.Observer;


public class DeleteSeriesController {

    public void deleteSeries(TvSeriesBean tvSeriesBean, Object object, Observer observer) {

        TvSeries tvSeries = new TvSeries(tvSeriesBean.getName());
        tvSeriesBean.register(observer);
        SeriesDAO.deleteSeries(tvSeries);
        tvSeriesBean.notifyObservers(object, "DE");
    }
}
