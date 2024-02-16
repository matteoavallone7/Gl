package com.example.ispw.cli_graphic_controller;

import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.controller.AddSeriesController;
import com.example.ispw.exceptions.SeriesNotFoundException;
import com.example.ispw.patterns.Observer;
import com.example.ispw.utilities.Printer;

import java.util.List;
import java.util.Locale;

public class AddSeriesCLIController implements Observer {

    private final static String NEWLY_ADDED = "NA";
    public void checkOccurrence(String name, List<TvSeriesBean> tvSeriesBeanList) throws SeriesNotFoundException {

            for (TvSeriesBean tvSeriesBean : tvSeriesBeanList) {
                if (tvSeriesBean.getName().equalsIgnoreCase(name)) {
                    tvSeriesBean.register(this);
                    AddSeriesController addSeriesController = new AddSeriesController();
                    addSeriesController.addSeries(tvSeriesBean, this);
                } else {
                    throw new SeriesNotFoundException();
                }
            }
    }

    @Override
    public void update(Object object, String status) {
        if (status.equals(NEWLY_ADDED)) {
            Printer.print("The series [" + ((TvSeriesBean)object).getName() + "] has been added to your watchlist.\n");
        } else {
            // Do nothing...
        }
    }

    @Override
    public void updateRealTime(TvSeriesBean tvSeriesBean, Object object, int changeList) {
        // Do nothing...
    }
}
