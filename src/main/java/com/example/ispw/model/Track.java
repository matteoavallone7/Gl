package com.example.ispw.model;

public class Track {

    private String author;
    private String title;
    private String imgSource;

    public Track(String author, String title, String imgSource) {
        this.author = author;
        this.title = title;
        this.imgSource = imgSource;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getImgSource() {
        return imgSource;
    }
}
