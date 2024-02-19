package com.example.ispw.bean;


import com.example.ispw.enums.SeriesAiringStatus;
import com.example.ispw.enums.SeriesStatus;
import com.example.ispw.patterns.Subject;

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

    private TvSeriesBean(Builder builder) {
        this.name = builder.name;
        this.seasons = builder.seasons;
        this.image = builder.image;
        this.plot = builder.plot;
        this.episodes = builder.episodes;
        this.genre = builder.genre;
        this.countryOfOrigin = builder.countryOfOrigin;
        this.rating = builder.rating;
        this.airingDate = builder.airingDate;
        if (builder.watchlistStatus != null) {
            this.watchlistStatus = builder.watchlistStatus.getId();
        }
    }

    public static class Builder {
        private String name;
        private int seasons;
        private String image;
        private String plot;
        private int episodes;
        private String genre;
        private String countryOfOrigin;
        private float rating;
        private LocalDate airingDate;
        private SeriesAiringStatus watchlistStatus;

        public Builder(String name, int seasons, String image, String plot, int episodes, String genre, String countryOfOrigin) {
            this.name = name;
            this.seasons = seasons;
            this.image = image;
            this.plot = plot;
            this.episodes = episodes;
            this.genre = genre;
            this.countryOfOrigin = countryOfOrigin;
        }

        public Builder rating(float rating) {
            this.rating = rating;
            return this;
        }

        public Builder airingDate(LocalDate airingDate) {
            this.airingDate = airingDate;
            return this;
        }

        public Builder watchlistStatus(SeriesAiringStatus watchlistStatus) {
            this.watchlistStatus = watchlistStatus;
            return this;
        }

        public TvSeriesBean build() {
            return new TvSeriesBean(this);
        }
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
