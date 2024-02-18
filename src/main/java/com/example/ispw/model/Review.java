package com.example.ispw.model;


import java.time.LocalDate;

public class Review {

    private int episodeId;
    private int season;
    private String tvSeries;
    private String reviewText;
    private float rating;
    private String username;
    private LocalDate pubDate;

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public Review(int episodeId, int season, String tvSeries, String reviewText, float rating, String username) {
        this.episodeId = episodeId;
        this.season = season;
        this.tvSeries = tvSeries;
        this.reviewText = reviewText;
        this.rating = rating;
        this.username = username;
    }

    public Review(String username, String reviewText, float rating, LocalDate pubDate) {
        this.username = username;
        this.reviewText = reviewText;
        this.rating = rating;
        this.pubDate = pubDate;
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

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
