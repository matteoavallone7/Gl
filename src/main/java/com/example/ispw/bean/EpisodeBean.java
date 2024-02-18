package com.example.ispw.bean;


import java.time.LocalDate;

public class EpisodeBean {

    private int id;
    private String overview;
    private String title;
    private String imgSrc;
    private int season;
    private double rating;
    private String runningTime;
    private String tvSeries;
    private LocalDate airingDate;
    private String timeslot;

    public EpisodeBean(int id, int season, LocalDate airingDate, String timeslot) {
        this.id = id;
        this.season = season;
        this.airingDate = airingDate;
        this.timeslot = timeslot;
    }

    public EpisodeBean(int id, int season, String tvSeries) {
        this.id = id;
        this.season = season;
        this.tvSeries = tvSeries;
    }

    private EpisodeBean(Builder builder) {
        this.id = builder.id;
        this.overview = builder.overview;
        this.title = builder.title;
        this.imgSrc = builder.imgSrc;
        this.tvSeries = builder.tvSeries;
        this.season = builder.season;
        this.rating = builder.rating;
        this.runningTime = builder.runningTime;
        this.airingDate = builder.airingDate;
    }

    public static class Builder {
        private int id;
        private String overview;
        private String title;
        private String imgSrc;
        private String tvSeries;
        private int season;
        private double rating;
        private String runningTime;
        private LocalDate airingDate;

        public Builder(int id, String tvSeries, int season) {
            this.id = id;
            this.tvSeries = tvSeries;
            this.season = season;
        }

        public Builder overview(String overview) {
            this.overview = overview;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder imgSrc(String imgSrc) {
            this.imgSrc = imgSrc;
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

        public EpisodeBean build() {
            return new EpisodeBean(this);
        }
    }

    public int getId() {
        return id;
    }

    public int getSeason() {
        return season;
    }

    public String getTvSeries() {
        return tvSeries;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public void setTvSeries(String tvSeries) {
        this.tvSeries = tvSeries;
    }

    public LocalDate getAiringDate() {
        return airingDate;
    }

    public void setAiringDate(LocalDate airingDate) {
        this.airingDate = airingDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }
}
