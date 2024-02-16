package com.example.ispw.bean;

public class ReviewBean extends EpisodeBean {

    private String reviewText;
    private float reviewRating;
    private String username;

    public ReviewBean(int id, int season, String tvSeries, String reviewText, float reviewRating, String username) {
        super(id, season, tvSeries);
        this.reviewRating = reviewRating;
        this.reviewText = reviewText;
        this.username = username;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(float reviewRating) {
        this.reviewRating = reviewRating;
    }
}
