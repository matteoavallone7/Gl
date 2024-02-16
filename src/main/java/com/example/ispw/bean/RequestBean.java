package com.example.ispw.bean;

public class RequestBean {

    private String series;
    private String request;
    private int frequency;

    public RequestBean(String series, String request) {
        this.series = series;
        this.request = request;
    }

    public RequestBean(String request, int frequency) {
        this.request = request;
        this.frequency = frequency;
    }

    public RequestBean() {

    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
