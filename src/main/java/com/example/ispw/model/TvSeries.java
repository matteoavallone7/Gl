package com.example.ispw.model;

import com.example.ispw.enums.SeriesAiringStatus;
import com.example.ispw.enums.SeriesStatus;

import java.time.LocalDate;

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

    private TvSeries(Builder builder) {
        this.name = builder.name;
        this.seasons = builder.seasons;
        this.plot = builder.plot;
        this.episodes = builder.episodes;
        this.genre = builder.genre;
        this.imgSource = builder.imgSource;
        this.countryOfOrigin = builder.countryOfOrigin;
        this.airingDate = builder.airingDate;
        this.airingStatus = builder.airingStatus;
        this.rating = builder.rating;
    }

    public static class Builder {
        private String name;
        private int seasons;
        private String plot;
        private int episodes;
        private String genre;
        private String imgSource;
        private String countryOfOrigin;
        private LocalDate airingDate;
        private SeriesAiringStatus airingStatus;
        private float rating;

        public Builder(String name, String imgSource, String genre, float rating) {
            this.name = name;
            this.imgSource = imgSource;
            this.genre = genre;
            this.rating = rating;
        }

        public Builder seasons(int seasons) {
            this.seasons = seasons;
            return this;
        }

        public Builder plot(String plot) {
            this.plot = plot;
            return this;
        }

        public Builder episodes(int episodes) {
            this.episodes = episodes;
            return this;
        }

        public Builder countryOfOrigin(String countryOfOrigin) {
            this.countryOfOrigin = countryOfOrigin;
            return this;
        }

        public Builder airingDate(LocalDate airingDate) {
            this.airingDate = airingDate;
            return this;
        }

        public Builder airingStatus(SeriesAiringStatus airingStatus) {
            this.airingStatus = airingStatus;
            return this;
        }

        public TvSeries build() {
            return new TvSeries(this);
        }
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
