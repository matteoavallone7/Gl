package com.example.ispw.model;

import com.example.ispw.enums.SeriesAiringStatus;
import com.example.ispw.enums.SeriesStatus;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class TvSeries {

    private String name;
    private int seasons;
    private String plot;

    private int episodes;
    private String imgSource;
    private String genre;
    private SeriesStatus status;
    private String countryOfOrigin;
    private LocalDate airingDate;
    private SeriesAiringStatus airingStatus;
    private float rating;

    public TvSeries(String name, int seasons, String plot, int episodes, String genre, String imgSource, String countryOfOrigin, LocalDate airingDate, SeriesAiringStatus
                    airingStatus, float rating){
        this(name, imgSource, genre, rating);
        this.seasons = seasons;
        this.plot = plot;
        this.episodes = episodes;
        this.countryOfOrigin = countryOfOrigin;
        this.airingDate = airingDate;
        this.airingStatus = airingStatus;
    }

    public TvSeries(String name, String imgSource, String genre, float rating) {
        this.name = name;
        this.imgSource = imgSource;
        this.genre = genre;
        this.rating = rating;
    }

    public TvSeries(String name, String imgSource, SeriesStatus status, LocalDate airingDate) {
        this.name = name;
        this.imgSource = imgSource;
        this.status = status;
        this.airingDate = airingDate;
    }

    public TvSeries(String name) {
        this.name = name;
    }

    public TvSeries(String name, String genre, LocalDate airingDate, String imgSource) {
        this.name = name;
        this.genre = genre;
        this.airingDate = airingDate;
        this.imgSource = imgSource;
    }

    public TvSeries(String name, String image, LocalDate airingDate) {
        this.name = name;
        this.imgSource = image;
        this.airingDate = airingDate;
    }

    public String getName() {
        return name;
    }

    public int getSeasons() {
        return seasons;
    }

    public String getPlot() {
        return plot;
    }

    public String getGenre() {
        return genre;
    }

    public int getEpisodes() {
        return episodes;
    }

    public String getImgSource() {
        return imgSource;
    }

    public SeriesStatus getStatus() {
        return status;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public LocalDate getAiringDate() {
        return airingDate;
    }

    public SeriesAiringStatus getAiringStatus() {
        return airingStatus;
    }

    public float getRating() {
        return rating;
    }
}
