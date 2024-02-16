package com.example.ispw.patterns;


import com.example.ispw.bean.TvSeriesBean;

public interface Observer {
    void update(Object object, String status);
    void updateRealTime(TvSeriesBean tvSeriesBean, Object object, int changeList);
}
