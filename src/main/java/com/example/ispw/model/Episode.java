package com.example.ispw.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class Episode {

    private int id;
    private String description;
    private String title;
    private String imgSource;
    private int season;
    private String tvSeries;
    private double rating;
    private String runningTime;
    private LocalDate airingDate;
    private String timeslot;


    private Episode(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.title = builder.title;
        this.imgSource = builder.imgSource;
        this.season = builder.season;
        this.tvSeries = builder.tvSeries;
        this.rating = builder.rating;
        this.runningTime = builder.runningTime;
        this.airingDate = builder.airingDate;
    }

    public static class Builder {
        private int id;
        private String description;
        private String title;
        private String imgSource;
        private int season;
        private String tvSeries;
        private double rating;
        private String runningTime;
        private LocalDate airingDate;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder imgSource(String imgSource) {
            this.imgSource = imgSource;
            return this;
        }

        public Builder season(int season) {
            this.season = season;
            return this;
        }

        public Builder tvSeries(String tvSeries) {
            this.tvSeries = tvSeries;
            return this;
        }

        public Builder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder runningTime(String runningTime) {
            this.runningTime = runningTime;
            return this;
        }

        public Builder airingDate(LocalDate airingDate) {
            this.airingDate = airingDate;
            return this;
        }

        public Episode build() {
            return new Episode(this);
        }
    }

    public Episode(int id, int season, LocalDate airingDate, String timeslot) {
        this.id = id;
        this.season = season;
        this.airingDate = airingDate;
        this.timeslot = timeslot;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getSeason() {
        return season;
    }

    public String getTvSeries() {
        return tvSeries;
    }

    public double getRating() {
        return rating;
    }

    public LocalDate getAiringDate() {
        return airingDate;
    }

    public String getTitle() {
        return title;
    }

    public String getImgSource() {
        return imgSource;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public String getTimeslot() {
        return timeslot;
    }
}
