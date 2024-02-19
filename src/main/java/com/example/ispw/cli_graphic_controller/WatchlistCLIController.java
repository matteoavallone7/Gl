package com.example.ispw.cli_graphic_controller;

import com.example.ispw.bean.EpisodeBean;
import com.example.ispw.bean.PartiallyWatchedEpBean;
import com.example.ispw.bean.TrackBean;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.cli_view.WatchlistViewCLI;
import com.example.ispw.controller.DeleteSeriesController;
import com.example.ispw.controller.DisplayWatchlistController;
import com.example.ispw.controller.MarkEpisodeController;
import com.example.ispw.exceptions.*;
import com.example.ispw.patterns.Observer;
import com.example.ispw.utilities.DateSupport;
import com.example.ispw.utilities.ExceptionSupport;
import com.example.ispw.utilities.Printer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WatchlistCLIController implements GraphicCLIController, Observer {

    private WatchlistViewCLI watchlistViewCLI;
    private static final  String MARK_EPISODE_AS_WATCHED = "1";
    private static final String MARK_EPISODE_AS_PARTIALLY_WATCHED = "2";
    private static final String EXPAND_EPISODE_DETAILS = "3";
    private static final String DELETE_SERIES = "4";
    private static final String RETURN_TO_HOMEPAGE = "5";
    private static final String CURRENTLY_WATCHING = "CURRENTLY WATCHING";
    private static final String NOT_YET_STARTED = "NOT YET STARTED";
    private static final String COMING_SOON = "COMING SOON";
    private static final String FINISHED_WATCHING = "FINISHED WATCHING";
    private static final String JUST_DELETED = "DE";
    private final List<TvSeriesBean> currentlyWatchingList = new ArrayList<>();
    private final List<TvSeriesBean> notYetStartedList = new ArrayList<>();
    private final List<TvSeriesBean> comingSoonList = new ArrayList<>();
    private final List<TvSeriesBean> finishedWatchingList = new ArrayList<>();

    @Override
    public void start() {
        DisplayWatchlistController displayWatchlistController = new DisplayWatchlistController();
        try {
            displayWatchlistController.getWatchlistLog(this);
        } catch (DatabaseException | DAOException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
        }
        this.watchlistViewCLI = new WatchlistViewCLI(this);
        showDetailedWatchlistItems();
        showOtherWatchlistItems();
        this.watchlistViewCLI.showMenu();
    }


    public void executeCommand(String inputLine) throws InvalidFormatException, SessionUserException, RatingFormatException, IOException, SeriesNotFoundException {
        switch (inputLine) {
            case MARK_EPISODE_AS_WATCHED -> markEpisodeAsWatched();
            case MARK_EPISODE_AS_PARTIALLY_WATCHED -> this.watchlistViewCLI.fetchPartialEpInfo();
            case EXPAND_EPISODE_DETAILS -> fetchDetails();
            case DELETE_SERIES -> this.watchlistViewCLI.fetchSeriesInfo();
            case RETURN_TO_HOMEPAGE -> new HomepageCLIController(1).start();
            default -> throw new InvalidFormatException();
        }
    }


    public void markEpisodeAsWatched() throws InvalidFormatException, RatingFormatException, IOException {
        EpisodeBean episodeBean = this.watchlistViewCLI.fetchEpInfo();
        MarkEpisodeController markEpisodeController = new MarkEpisodeController();
        TvSeriesBean tvSeries = new TvSeriesBean(episodeBean.getTvSeries());
        markEpisodeController.markEpAsFullyWatched(episodeBean, tvSeries, this);
        Printer.print("\n\nWant to leave a review? (y/n)\n");
        String choice = Printer.getChoice();
        if (choice.equals("y")) {
            ReviewCLIController reviewCLIController = new ReviewCLIController(episodeBean);
            reviewCLIController.start();
            this.watchlistViewCLI.showMenu();
        } else if (choice.equals("n")) {
            showDetailedWatchlistItems();
            showOtherWatchlistItems();
            this.watchlistViewCLI.showMenu();
        } else {
            throw new InvalidFormatException("Two possible answers: 'y' or 'n'.\n");
        }
    }

    public void markEpisodeAsPartiallyWatched(int episodeNumber, int season, String tvSeries, String timeslot) throws InvalidFormatException {
        PartiallyWatchedEpBean partiallyWatchedEpBean = new PartiallyWatchedEpBean(episodeNumber, season, tvSeries, timeslot);
        MarkEpisodeController markEpisodeController = new MarkEpisodeController();
        markEpisodeController.markEpAsPartiallyWatched(partiallyWatchedEpBean, partiallyWatchedEpBean, this);
        start();
    }

    public void deleteSeries(String series) throws SeriesNotFoundException {
        boolean found = false;

        for (List<TvSeriesBean> seriesList : Arrays.asList(currentlyWatchingList, notYetStartedList, comingSoonList, finishedWatchingList)) {
            for (TvSeriesBean tvSeriesBean : seriesList) {
                if (tvSeriesBean.getName().equals(series)) {
                    DeleteSeriesController deleteSeriesController = new DeleteSeriesController();
                    deleteSeriesController.deleteSeries(tvSeriesBean, tvSeriesBean, this);
                    found = true;
                    break; // No need to continue checking other elements once found
                }
            }
            if (found) {
                break; // No need to continue checking other lists once found
            }
        }

        // If the series was not found in any list, throw an exception
        if (!found) {
            throw new SeriesNotFoundException();
        }
    }

    public void showDetailedWatchlistItems() {
        DisplayWatchlistController displayWatchlistController = new DisplayWatchlistController();
        this.watchlistViewCLI.showSeparatorLine();
        this.watchlistViewCLI.showListSeparatorLine(CURRENTLY_WATCHING);
        for (TvSeriesBean tvSeriesBean : currentlyWatchingList) {
            EpisodeBean episodeBean;
            episodeBean = displayWatchlistController.getLatestUnseenEpisode(tvSeriesBean);
            this.watchlistViewCLI.printDetailedLists(tvSeriesBean, episodeBean);
        }
        this.watchlistViewCLI.showListSeparatorLine(NOT_YET_STARTED);
        for (TvSeriesBean tvSeriesBean : notYetStartedList) {
            EpisodeBean episodeBean;
            episodeBean = displayWatchlistController.getLatestUnseenEpisode(tvSeriesBean);
            this.watchlistViewCLI.printDetailedLists(tvSeriesBean, episodeBean);
        }
    }

    public void showOtherWatchlistItems() {
        this.watchlistViewCLI.showListSeparatorLine(COMING_SOON);
        for (TvSeriesBean tvSeriesBean : comingSoonList) {
            long daysLeft = DateSupport.daysLeft(tvSeriesBean.getAiringDate());
            this.watchlistViewCLI.printAiringSoonList(tvSeriesBean, daysLeft);
        }
        this.watchlistViewCLI.showListSeparatorLine(FINISHED_WATCHING);
        for (TvSeriesBean tvSeriesBean : finishedWatchingList) {
            this.watchlistViewCLI.printList(tvSeriesBean);
        }
    }

    public void fetchDetails() {
        EpisodeBean episodeBean = this.watchlistViewCLI.fetchEpInfo();
        DisplayWatchlistController displayWatchlistController = new DisplayWatchlistController();
        EpisodeBean episode = displayWatchlistController.getEpisodeDetails(episodeBean);
        List<TrackBean> trackList = displayWatchlistController.getEpisodeMusicInfo(episodeBean);
        int choice = this.watchlistViewCLI.printEpDetails(episode, trackList);
        if (choice == 1) {
            RequestCLIController requestCLIController = new RequestCLIController();
            requestCLIController.buildRequest(episodeBean);
            this.watchlistViewCLI.showMenu();
        } else {
            this.watchlistViewCLI.showMenu();
        }
    }

    @Override
    public void update(Object object, String status) {
        if (status.equals(CURRENTLY_WATCHING)) {
            this.currentlyWatchingList.add(((TvSeriesBean)object));
        } else if (status.equals(NOT_YET_STARTED)) {
            this.notYetStartedList.add(((TvSeriesBean)object));
        } else if (status.equals(COMING_SOON)) {
            this.comingSoonList.add(((TvSeriesBean)object));
        } else if (status.equals(FINISHED_WATCHING)) {
            this.finishedWatchingList.add(((TvSeriesBean)object));
        } else if (status.equals(JUST_DELETED)){
            Printer.print("You have just deleted " + ((TvSeriesBean)object).getName() + " from your watchlist.\n");
            start();
        } else if (status.equals("Partially watched")){
            Printer.print("You've just set an episode on hold, don't forget to finish watching it!\n");
        } else {
            // Do nothing...
        }
    }

    @Override
    public void updateRealTime(TvSeriesBean tvSeriesBean, Object object, int change) {
        boolean found = false;

        for (List<TvSeriesBean> seriesList : Arrays.asList(currentlyWatchingList, notYetStartedList, comingSoonList, finishedWatchingList)) {
            for (TvSeriesBean tvSeriesBean1 : seriesList) {
                if (tvSeriesBean1.getName().equals(tvSeriesBean.getName())) {
                    seriesList.remove(tvSeriesBean1);
                    if (change == 1) {
                        this.currentlyWatchingList.add(tvSeriesBean);
                    } else if (change == 2) {
                        this.notYetStartedList.add(tvSeriesBean);
                    } else if (change == 3) {
                        this.comingSoonList.add(tvSeriesBean);
                    } else {
                        this.finishedWatchingList.add(tvSeriesBean);
                    }
                    found = true;
                    break; // No need to continue checking other elements once found
                }
            }
            if (found) {
                break; // No need to continue checking other lists once found
            }
        }
    }
}
