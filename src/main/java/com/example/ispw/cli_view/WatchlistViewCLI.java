package com.example.ispw.cli_view;

import com.example.ispw.bean.EpisodeBean;
import com.example.ispw.bean.TrackBean;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.cli_graphic_controller.WatchlistCLIController;
import com.example.ispw.exceptions.InvalidFormatException;
import com.example.ispw.exceptions.RatingFormatException;
import com.example.ispw.exceptions.SeriesNotFoundException;
import com.example.ispw.exceptions.SessionUserException;
import com.example.ispw.model.Episode;
import com.example.ispw.utilities.ExceptionSupport;
import com.example.ispw.utilities.Printer;
import com.example.ispw.utilities.ValidateTimeslot;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class WatchlistViewCLI {

    private final WatchlistCLIController watchlistCLIController;

    public WatchlistViewCLI(WatchlistCLIController watchlistCLIController) {
        this.watchlistCLIController = watchlistCLIController;
    }

    public void showMenu() {
        Printer.print("*** What should I do for you? ***\n");
        Printer.print("1) Mark episode as watched\n");
        Printer.print("2) Mark episode as partially watched\n");
        Printer.print("3) Expand episode details\n");
        Printer.print("4) Delete tv series\n");
        Printer.print("5) Return to Homepage\n");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.watchlistCLIController.executeCommand(inputLine);
        } catch (InvalidFormatException | SessionUserException | RatingFormatException | IOException | SeriesNotFoundException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
            showMenu();
        }
    }


    public EpisodeBean fetchEpInfo() {
        Scanner scanner = new Scanner(System.in);
        Printer.print("Tv series:");
        String tvSeries = scanner.nextLine();
        Printer.print("Season number: ");
        int season = scanner.nextInt();
        Printer.print("Episode number: ");
        int episodeNumber = scanner.nextInt();
        return new EpisodeBean(episodeNumber, season, tvSeries);
    }

    public void fetchPartialEpInfo() throws InvalidFormatException {
        Scanner scanner = new Scanner(System.in);
        Printer.print("Tv series:");
        String tvSeries = scanner.nextLine();
        Printer.print("Season number: ");
        int season = scanner.nextInt();
        Printer.print("Episode number: ");
        int episodeNumber = scanner.nextInt();
        scanner.nextLine();
        Printer.print("Valid timeslot [hh:mm:ss]: ");
        String timeslot = scanner.nextLine();
        if (ValidateTimeslot.isValid(timeslot)) {
            watchlistCLIController.markEpisodeAsPartiallyWatched(episodeNumber, season, tvSeries, timeslot);
        } else {
            throw new InvalidFormatException("The only valid time format is [hh:mm:ss]");
        }
    }

    public void fetchSeriesInfo() throws SeriesNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Printer.print("Tv series to be deleted: ");
        String series = scanner.nextLine();
        this.watchlistCLIController.deleteSeries(series);
    }


    public void showSeparatorLine() {
        Printer.print("------------------------------- THIS IS YOUR WATCHLIST -------------------------------");
    }

    public void showListSeparatorLine(String separator) {
        Printer.print("///" + separator + "///\n");
    }

    public void printDetailedLists(TvSeriesBean tvSeriesBean, EpisodeBean episodeBean) {
        Printer.print("[" + tvSeriesBean.getName() + "]" + ", next episode: S0" + episodeBean.getSeason() + "E" + episodeBean.getId() + "\n");
    }

    public void printAiringSoonList(TvSeriesBean tvSeriesBean, long numOfDaysLeft) {
        Printer.print("[" + tvSeriesBean.getName() + "]" + ", days left until release: " + numOfDaysLeft + "\n");
    }

    public void printList(TvSeriesBean tvSeriesBean) {
        Printer.print("[" + tvSeriesBean.getName() + "]\n");
    }

    public int printEpDetails(EpisodeBean episodeBean, List<TrackBean> list) {
        Printer.print("S" + episodeBean.getSeason() + "E" + episodeBean.getId());
        Printer.print("\nTitle: " + episodeBean.getTitle());
        Printer.print("\nOverview:\n" + episodeBean.getOverview());
        Printer.print("Rating: " + episodeBean.getRating() + ", Airing Date: " + episodeBean.getAiringDate());
        if (episodeBean.getTimeslot() != null) {
            Printer.print("\nTimeslot: " + episodeBean.getTimeslot());
        }
        Printer.print("\n----------------FEATURED MUSIC----------------\n");
        for (TrackBean trackBean : list) {
            Printer.print("- Title: " + trackBean.getTitle() + ", Author: " + trackBean.getAuthor() + "\n");
        }
        Printer.print("\nFeel like the track list is incomplete? (yes/no)");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        if (choice.equals("yes")) {
            return 1;
        } else {
            return 0;
        }
    }


}
