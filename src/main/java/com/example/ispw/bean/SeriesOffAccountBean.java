package com.example.ispw.bean;


public class SeriesOffAccountBean {

    private String seriesName;
    private String username;
    private String email;
    private int verificationId;

    public SeriesOffAccountBean(String seriesName, String username, String email, int verificationId) {
        this.seriesName = seriesName;
        this.username = username;
        this.email = email;
        this.verificationId = verificationId;
    }

    public String getUsername() {
        return username;
    }
    public String getSeriesName() {
        return seriesName;
    }

    public String getEmail() {
         return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(int verificationId) {
        this.verificationId = verificationId;
    }
}
