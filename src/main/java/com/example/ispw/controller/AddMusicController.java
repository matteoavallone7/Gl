package com.example.ispw.controller;

import com.example.ispw.bean.SearchBean;
import com.example.ispw.bean.TrackBean;
import com.example.ispw.dao.EpisodeDAO;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.model.SearchModel;
import com.example.ispw.model.Track;
import com.example.ispw.session.Session;

import java.util.ArrayList;
import java.util.List;

public class AddMusicController {

    public boolean verifyEpisode(int episode, int season) {
        String series = Session.getCurrentSession().getSeriesOffAccountBean().getSeriesName();
        int verify = EpisodeDAO.verifyEpisode(episode, season, series);
        if (verify == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addMusicToEpisode(int episode, int season, TrackBean trackBean) {
        String series = Session.getCurrentSession().getSeriesOffAccountBean().getSeriesName();
        EpisodeDAO.addTrack(episode, season, trackBean.getAuthor(), trackBean.getTitle(), series);
    }

    public List<TrackBean> searchMusic(SearchBean searchBean) throws DAOException {

        SearchModel searchModel = new SearchModel();
        searchModel.setName(searchBean.getName());
        List<TrackBean> trackBeans = new ArrayList<>();
        List<Track> trackList;

        trackList = EpisodeDAO.getSearchResults(searchModel);
        for (Track track: trackList) {
            TrackBean trackBean = new TrackBean(track.getAuthor(), track.getTitle(), track.getImgSource());
            trackBeans.add(trackBean);
        }
        return trackBeans;
    }
}
