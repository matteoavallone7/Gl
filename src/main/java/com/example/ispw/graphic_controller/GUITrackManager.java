package com.example.ispw.graphic_controller;

import com.example.ispw.bean.TrackBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class GUITrackManager {

    @FXML
    private Label artist;

    @FXML
    private Label title;

    @FXML
    private ImageView trackImg;

    public void buildTrackInfo(TrackBean trackBean) {
        artist.setText(trackBean.getAuthor());
        title.setText(trackBean.getTitle());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(trackBean.getImgSrc())));
        trackImg.setImage(image);
    }
}
