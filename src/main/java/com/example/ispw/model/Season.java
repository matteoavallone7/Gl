package com.example.ispw.model;

public class Season {

    private int seasonNum;
    private int watchlistId;

    public Season(int seasonNum, int watchlistId) {
        this.seasonNum = seasonNum;
        this.watchlistId = watchlistId;
    }

    public int getSeasonNum() {
        return seasonNum;
    }

    public int getWatchlistId() {
        return watchlistId;
    }

    public void setSeasonNum(int seasonNum) {
        this.seasonNum = seasonNum;
    }

    public void setWatchlistId(int watchlistId) {
        this.watchlistId = watchlistId;
    }
}
