package com.example.ispw.model;


import java.time.LocalDate;

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


   public Episode(int id, String description, String title, String imgSource, int season, double rating, LocalDate airingDate) {
       this.id = id;
       this.description = description;
       this.title = title;
       this.imgSource = imgSource;
       this.season = season;
       this.rating = rating;
       this.airingDate = airingDate;
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
