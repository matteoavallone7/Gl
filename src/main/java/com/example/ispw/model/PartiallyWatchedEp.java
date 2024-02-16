package com.example.ispw.model;

public class PartiallyWatchedEp {

    private String timeslot;
    private int id;
    private int season;
    private String tvSeries;

    public PartiallyWatchedEp(int id, int season, String tvSeries, String timeslot) {
        this.id = id;
        this.season = season;
        this.tvSeries = tvSeries;
        this.timeslot = timeslot;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getTvSeries() {
        return tvSeries;
    }

    public void setTvSeries(String tvSeries) {
        this.tvSeries = tvSeries;
    }
}
