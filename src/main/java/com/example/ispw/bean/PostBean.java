package com.example.ispw.bean;


import java.time.LocalDate;

public class PostBean {

    private String author;
    private String title;
    private String description;
    private String imageSource;
    private LocalDate date;

    public PostBean() {}

    public PostBean(String author, String title, String description, String imageSource, LocalDate date){
        this(author, title, description, imageSource);
        this.date = date;
    }

    public PostBean(String author, String title, String description, String imageSource) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.imageSource = imageSource;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
