package com.example.ispw.controller;

import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.dao.SeriesDAO;
import com.example.ispw.enums.SeriesStatus;
import com.example.ispw.model.TvSeries;
import com.example.ispw.patterns.Observer;


public class AddSeriesController {

    public void addSeries(Object object, Observer observer) {

        TvSeries tvSeries = new TvSeries(((TvSeriesBean)object).getName());
        SeriesDAO.addSeries(tvSeries, SeriesStatus.COMING_SOON.getId());

        ((TvSeriesBean)object).register(observer);
        ((TvSeriesBean)object).notifyObservers(object, "NA");
    }
}
