package com.example.ispw.model;

public class SeriesOffAccount extends GenericUser {

    private int verificationId;
    private String seriesName;

    public SeriesOffAccount(String username, String email, int verificationId, String seriesName) {
        super(username, email);
        this.verificationId = verificationId;
        this.seriesName = seriesName;
    }

    public int getVerificationId() {
        return verificationId;
    }

    public String getSeriesName() {
        return seriesName;
    }
}
