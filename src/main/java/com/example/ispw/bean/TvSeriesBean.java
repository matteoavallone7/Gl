package com.example.ispw.bean;


import com.example.ispw.enums.SeriesAiringStatus;
import com.example.ispw.enums.SeriesStatus;
import com.example.ispw.patterns.Subject;

import java.io.File;
import java.time.LocalDate;

public class TvSeriesBean extends Subject {

    private String name;
    private int seasons;
    private String plot;
    private int episodes;
    private String image;
    private String genre;
    private LocalDate airingDate;
    private String countryOfOrigin;
    private String watchlistStatus; // 0 -> 'currently watching' | 1 -> 'not yet started' | 2 -> 'coming soon' | 3 -> 'finished watching'
    private float rating;

    public TvSeriesBean(String name) {
        this.name = name;
    }

    public TvSeriesBean(String name, int seasons, String image, String plot, int episodes, String genre, String countryOfOrigin, float rating) {
        this.name = name;
        this.seasons = seasons;
        this.image = image;
        this.plot = plot;
        this.episodes = episodes;
        this.genre = genre;
        this.countryOfOrigin = countryOfOrigin;
        this.rating = rating;
    }


    public TvSeriesBean(String name, int seasons, String image, String plot, int episodes, String genre, String countryOfOrigin, float rating, LocalDate airingDate, SeriesAiringStatus watchlistStatus) {
        this(name, seasons, image, plot, episodes, genre, countryOfOrigin, rating);
        this.airingDate = airingDate;
        this.watchlistStatus = watchlistStatus.getId();
    }

    public TvSeriesBean(String name, String image, SeriesStatus watchlistStatus, LocalDate airingDate) {
        this.name = name;
        this.image = image;
        this.watchlistStatus = watchlistStatus.getId();
        this.airingDate = airingDate;
    }

    public TvSeriesBean(String name, String genre, LocalDate airingDate, String image) {
        this.name = name;
        this.genre = genre;
        this.airingDate = airingDate;
        this.image = image;
    }

    public TvSeriesBean(String name, String image, LocalDate airingDate) {
        this.name = name;
        this.image = image;
        this.airingDate = airingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeasons() { return seasons; }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public int getEpisodes() { return episodes; }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setWatchlistStatus(String watchlistStatus) {
        this.watchlistStatus = watchlistStatus;
    }

    public String getWatchlistStatus() {
        return watchlistStatus;
    }

    public LocalDate getAiringDate() {
        return airingDate;
    }

    public void setAiringDate(LocalDate airingDate) {
        this.airingDate = airingDate;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
