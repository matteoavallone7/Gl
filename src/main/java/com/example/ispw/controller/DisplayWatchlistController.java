package com.example.ispw.controller;

import com.example.ispw.bean.EpisodeBean;
import com.example.ispw.bean.TrackBean;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.dao.EpisodeDAO;
import com.example.ispw.dao.SeriesDAO;
import com.example.ispw.dao.WatchlistDAO;
import com.example.ispw.enums.SeriesStatus;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.exceptions.DatabaseException;
import com.example.ispw.exceptions.EpisodeException;
import com.example.ispw.exceptions.RatingErrorException;
import com.example.ispw.model.Episode;
import com.example.ispw.model.Track;
import com.example.ispw.model.TvSeries;
import com.example.ispw.patterns.Observer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DisplayWatchlistController {

    private final static String CURRENTLY_WATCHING = "CURRENTLY WATCHING";
    private final static String NOT_YET_STARTED = "NOT YET STARTED";
    private final static String COMING_SOON = "COMING SOON";
    private final static String FINISHED_WATCHING = "FINISHED WATCHING";

    public void getWatchlistLog(Observer observer) throws DatabaseException, SQLException, DAOException {

        List<TvSeries> tvSeriesList = WatchlistDAO.getWatchlistSeries();

        for (TvSeries tvSeries : tvSeriesList) {
            TvSeriesBean tvSeriesBean = new TvSeriesBean(tvSeries.getName(), tvSeries.getImgSource(), tvSeries.getStatus(), tvSeries.getAiringDate());
            tvSeriesBean.register(observer);
            switch (tvSeriesBean.getWatchlistStatus()) {
                case "Currently watching" -> tvSeriesBean.notifyObservers(tvSeriesBean, CURRENTLY_WATCHING);
                case "Not yet started" -> tvSeriesBean.notifyObservers(tvSeriesBean, NOT_YET_STARTED);
                case "Coming soon" -> {
                    if (tvSeriesBean.getAiringDate().isBefore(LocalDate.now())) {
                        WatchlistDAO.updateStatus(tvSeriesBean.getName(), SeriesStatus.NOT_YET_STARTED);
                        tvSeriesBean.notifyObservers(tvSeriesBean, NOT_YET_STARTED);
                    } else {
                        tvSeriesBean.notifyObservers(tvSeriesBean, COMING_SOON);
                    }
                }
                case "Finished watching" -> {
                    if (tvSeriesBean.getAiringDate().isAfter(LocalDate.now())) { // renewing a tv show implies new airing date
                        WatchlistDAO.updateStatus(tvSeriesBean.getName(), SeriesStatus.COMING_SOON);
                        tvSeriesBean.notifyObservers(tvSeriesBean, COMING_SOON);
                    } else {
                        tvSeriesBean.notifyObservers(tvSeriesBean, FINISHED_WATCHING);
                    }
                }
                default -> throw new DatabaseException("Database error: unexpected behaviour");
            }
        }
    }

    public EpisodeBean getLatestUnseenEpisode(TvSeriesBean tvSeriesBean) {

        Episode episode = EpisodeDAO.getLatestEpisode(tvSeriesBean.getName());
        return new EpisodeBean(episode.getId(), episode.getSeason(), episode.getAiringDate(), episode.getTimeslot());
    }

    public EpisodeBean getEpisodeDetails(EpisodeBean episodeBean) {

        Episode episode = EpisodeDAO.getEpDetails(episodeBean.getId(), episodeBean.getSeason(), episodeBean.getTvSeries());
        return new EpisodeBean.Builder(episode.getId(), episode.getTvSeries(), episode.getSeason()).overview(episode.getDescription()).title(episode.getTitle()).imgSrc(episode.getImgSource()).rating(episode.getRating()).runningTime(episode.getRunningTime()).airingDate(episode.getAiringDate()).build();
    }

    public List<TrackBean> getEpisodeMusicInfo(EpisodeBean episodeBean) {
        List<TrackBean> trackBeanList = new ArrayList<>();

        List<Track> trackList = EpisodeDAO.getEpTracks(episodeBean.getId(), episodeBean.getSeason(), episodeBean.getTvSeries());
        for (Track track : trackList) {
            TrackBean trackBean = new TrackBean(track.getAuthor(), track.getTitle(), track.getImgSource());
            trackBeanList.add(trackBean);
        }

        return trackBeanList;
    }

    public TvSeriesBean getTvSeriesDet(TvSeriesBean tvSeriesBean) {
        TvSeries tvSeries = SeriesDAO.getTvSeriesDet(tvSeriesBean.getName());
        return new TvSeriesBean(tvSeries.getName(), tvSeries.getImgSource(), tvSeries.getAiringDate());
    }
}
