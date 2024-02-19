package com.example.ispw.controller;

import com.example.ispw.bean.EpisodeBean;
import com.example.ispw.bean.PartiallyWatchedEpBean;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.dao.EpisodeDAO;
import com.example.ispw.dao.WatchlistDAO;
import com.example.ispw.enums.SeriesStatus;
import com.example.ispw.exceptions.InvalidFormatException;
import com.example.ispw.model.PartiallyWatchedEp;
import com.example.ispw.patterns.Observer;
import com.example.ispw.utilities.ValidateTimeslot;

import java.time.LocalDate;
import java.util.Objects;

public class MarkEpisodeController {

    private static final int CHANGE_TO_CURRENTLY_WATCHING = 1;
    private static final int CHANGE_TO_NOT_YET_STARTED = 2;
    private static final int CHANGE_TO_COMING_SOON = 3;
    private static final int CHANGE_TO_FINISHED_WATCHING = 4;


    public void markEpAsFullyWatched(EpisodeBean episodeBean, Object object, Observer observer) {

        TvSeriesBean tvSeriesBean = new TvSeriesBean(episodeBean.getTvSeries());
        tvSeriesBean.register(observer);
        EpisodeDAO.updateFullEp(episodeBean.getId(), episodeBean.getSeason(), episodeBean.getTvSeries());
        DisplayWatchlistController displayWatchlistController = new DisplayWatchlistController();
        EpisodeBean episode = displayWatchlistController.getLatestUnseenEpisode(tvSeriesBean);

        if (episode.getId() == 2) {
            tvSeriesBean.notify(tvSeriesBean, object, CHANGE_TO_CURRENTLY_WATCHING);
            WatchlistDAO.updateStatus(tvSeriesBean.getName(), SeriesStatus.CURRENTLY_WATCHING);
        } else if ((Objects.equals(episode.getSeason(), episodeBean.getSeason()))){
            tvSeriesBean.notify(tvSeriesBean, object, CHANGE_TO_CURRENTLY_WATCHING); // stay in the same list
        } else if ((!Objects.equals(episode.getSeason(), episodeBean.getSeason())) && episode.getAiringDate().isBefore(LocalDate.now())) {
            tvSeriesBean.notify(tvSeriesBean, object, CHANGE_TO_NOT_YET_STARTED);
            WatchlistDAO.updateStatus(tvSeriesBean.getName(), SeriesStatus.NOT_YET_STARTED);
        } else if ((!Objects.equals(episode.getSeason(), episodeBean.getSeason())) && episode.getAiringDate().isAfter(LocalDate.now())) {
            tvSeriesBean.notify(tvSeriesBean, object, CHANGE_TO_COMING_SOON);
            WatchlistDAO.updateStatus(tvSeriesBean.getName(), SeriesStatus.COMING_SOON);
        } else {
            tvSeriesBean.notify(tvSeriesBean, object, CHANGE_TO_FINISHED_WATCHING);
            WatchlistDAO.updateStatus(tvSeriesBean.getName(), SeriesStatus.FINISHED_WATCHING);
        }
    }

    public void markEpAsPartiallyWatched(PartiallyWatchedEpBean partiallyWatchedEpBean, Object object, Observer observer) throws InvalidFormatException {

        partiallyWatchedEpBean.register(observer);
        String runningTime = EpisodeDAO.getEpisodeRunningTime(partiallyWatchedEpBean.getId(), partiallyWatchedEpBean.getSeason(), partiallyWatchedEpBean.getTvSeries());
        if (ValidateTimeslot.isValid(partiallyWatchedEpBean.getTimeslot()) &&
                ValidateTimeslot.getMinutes(partiallyWatchedEpBean.getTimeslot(), runningTime)) {
            PartiallyWatchedEp partiallyWatchedEp = new PartiallyWatchedEp(partiallyWatchedEpBean.getId(), partiallyWatchedEpBean.getSeason(), partiallyWatchedEpBean.getTvSeries(), partiallyWatchedEpBean.getTimeslot());
            EpisodeDAO.updatePartialEp(partiallyWatchedEp);
            partiallyWatchedEpBean.notifyObservers(object, "Partially watched");
        } else {
            throw new InvalidFormatException("Either the timeslot's format is wrong or the episode's length is less than the timeslot");
        }
    }

}
