package com.example.ispw.graphic_controller;

import com.example.ispw.bean.TrackBean;
import com.example.ispw.controller.AddMusicController;
import com.example.ispw.utilities.ValidateTimeslot;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class GUIMusicResultsController {

    @FXML
    private Label author;

    @FXML
    private ImageView songImg;

    @FXML
    private Label title;

    private int episode;
    private int season;

    public void buildMusicLayout(TrackBean trackBean, int episode, int season) {
        author.setText(trackBean.getAuthor());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(trackBean.getImgSrc())));
        songImg.setImage(image);
        title.setText(trackBean.getTitle());
        this.episode = episode;
        this.season = season;
    }

    public void addSongToEpisode() {
        TrackBean trackBean = new TrackBean(author.getText(), title.getText());
        AddMusicController addMusicController = new AddMusicController();
        addMusicController.addMusicToEpisode(episode, season, trackBean);
    }

}
