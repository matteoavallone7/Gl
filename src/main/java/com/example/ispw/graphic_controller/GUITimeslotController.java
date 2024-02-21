package com.example.ispw.graphic_controller;

import com.example.ispw.bean.PartiallyWatchedEpBean;
import com.example.ispw.controller.MarkEpisodeController;
import com.example.ispw.exceptions.InvalidFormatException;
import com.example.ispw.patterns.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GUITimeslotController {

    @FXML
    private Label errorLbl;

    @FXML
    private TextField text;

    private int episode;
    private int season;
    private String tvSeries;

    private Observer observer;

    private Parent banner;

    public void setBanner(Parent banner) {
        this.banner = banner;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void setInfo(int episode, int season, String tvSeries) {
        this.episode = episode;
        this.season = season;
        this.tvSeries = tvSeries;
    }

    public void submitTimeslot() {
        MarkEpisodeController markEpisodeController = new MarkEpisodeController();
        PartiallyWatchedEpBean partiallyWatchedEpBean = new PartiallyWatchedEpBean(episode, season, tvSeries, text.getText());
        try {
            markEpisodeController.markEpAsPartiallyWatched(partiallyWatchedEpBean, banner, observer);
        } catch (InvalidFormatException e) {
            errorLbl.setText("Wrong format and/or timestamp");
            errorLbl.setVisible(true);
            text.clear();
        }

    }


    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }


}
