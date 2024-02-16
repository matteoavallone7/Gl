package com.example.ispw.model;

public class Request {

    private String series;
    private String description;
    private int frequency;

    public Request(String series, String description) {
        this.series = series;
        this.description = description;
    }

    public Request(int frequency, String description) {
        this.description = description;
        this.frequency = frequency;
    }

    public Request(String description) {
        this.description = description;
    }

    public String getSeries() {
        return series;
    }

    public String getDescription() {
        return description;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


}
