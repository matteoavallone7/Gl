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

    public EpisodeBean(int id, String overview, String title, String imgSrc, String tvSeries, int season, double rating, String runningTime, LocalDate airingDate) {
        this(id, season, tvSeries);
        this.airingDate = airingDate;
        this.overview = overview;
        this.title = title;
        this.imgSrc = imgSrc;
        this.rating = rating;
        this.runningTime = runningTime;
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
