package com.example.ispw.bean;

public class TrackBean {

    private String author;
    private String title;
    private String imgSrc;

    public TrackBean(String author, String title, String imgSrc) {
        this(author, title);
        this.imgSrc = imgSrc;
    }

    public TrackBean(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
