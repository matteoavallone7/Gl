package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.bean.PostBean;
import com.example.ispw.controller.NewsController;
import com.example.ispw.model.Post;
import com.example.ispw.session.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.util.Objects;

public class GUINewsManager {

    @FXML
    private Label account;

    @FXML
    private Label date;

    @FXML
    private ImageView img;

    @FXML
    private TextArea textArea;
    @FXML
    private Label title;

    public void buildNewsFeed(PostBean postBean) throws IOException {
        account.setText(postBean.getAuthor());
        date.setText(String.valueOf(postBean.getDate()));
        title.setText(postBean.getTitle());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(postBean.getImageSource())));
        img.setImage(image);
        textArea.setText(postBean.getDescription());
    }

}
