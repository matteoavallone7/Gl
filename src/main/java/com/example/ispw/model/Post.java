package com.example.ispw.model;

import java.io.File;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Date;

public class Post {

    private String author;
    private String title;
    private String description;
    private String imageSource;
    private LocalDate pubDate;

    public Post(String description, String author, String title, String imageSource, LocalDate pubDate){
        this(author, title, description, imageSource);
        this.pubDate = pubDate;
    }

    public Post(String author, String title, String description, String imageSource) {
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

    public LocalDate getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDate pubDate) {
        this.pubDate = pubDate;
    }
}
