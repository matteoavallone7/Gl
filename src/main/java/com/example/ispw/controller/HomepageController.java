package com.example.ispw.controller;

import com.example.ispw.bean.PostBean;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.dao.NewsDAO;
import com.example.ispw.dao.SeriesDAO;
import com.example.ispw.model.Post;
import com.example.ispw.model.TvSeries;

import java.util.ArrayList;
import java.util.List;

public class HomepageController {

    public PostBean getPostOverview() {
        Post post = NewsDAO.getNewsOverview();
        return new PostBean(post.getAuthor(), post.getTitle(), post.getDescription(), post.getImageSource(), post.getPubDate());
    }

    public List<TvSeriesBean> getRandomSeries() {
        List<TvSeriesBean> tvSeriesBeanList = new ArrayList<>();
        List<TvSeries> tvSeriesList;

        tvSeriesList = SeriesDAO.randomSeries();
        for (TvSeries tvSeries : tvSeriesList) {
            TvSeriesBean tvSeriesBean = new TvSeriesBean(tvSeries.getName(), tvSeries.getGenre(), tvSeries.getAiringDate(), tvSeries.getImgSource());
            tvSeriesBeanList.add(tvSeriesBean);
        }

        return tvSeriesBeanList;
    }

}
