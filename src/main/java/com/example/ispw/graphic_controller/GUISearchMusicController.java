package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.bean.SearchBean;
import com.example.ispw.bean.TrackBean;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.controller.AddMusicController;
import com.example.ispw.controller.AddSeriesController;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.exceptions.EpisodeException;
import com.example.ispw.utilities.ExceptionSupport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GUISearchMusicController {

    @FXML
    private TextField searchText;
    @FXML
    private VBox vbox;
    private int episodeNumber;
    private int seasonNumber;
    private AddMusicController addMusicController;

    private Parent previousPage;

    public void setPreviousPage(Parent previousPage) {
        this.previousPage = previousPage;
    }

    public void initialize(int ep, int season, Parent previousPage) throws IOException {
        this.addMusicController = new AddMusicController();
        setPreviousPage(previousPage);
        verify(ep, season);
    }

    public void verify(int ep, int season) {

        try {
            if (addMusicController.verifyEpisode(ep, season)) {
                this.episodeNumber = ep;
                this.seasonNumber = season;
            } else {
                throw new EpisodeException();
            }

        } catch (EpisodeException e) {
            ExceptionSupport.showExceptionGUI("Wrong values", e.getMessage());
        }
    }

    public void searchTrack() {
        try {
            SearchBean searchBean = new SearchBean();
            searchBean.setName(searchText.getText());
            List<TrackBean> trackBeans = addMusicController.searchMusic(searchBean);
            setData(trackBeans);

        } catch (DAOException e) {
            ExceptionSupport.showExceptionGUI("Search error", e.getMessage());
        }
    }

    public void hidePage(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = this.previousPage.getScene();
        stage.setScene(scene);
    }

    public void setData(List<TrackBean> trackBeans) {

        vbox.getChildren().clear(); // Clear existing content
        try {
            for (TrackBean trackBean: trackBeans) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Objects.requireNonNull(Main.class.getResource("/SearchedSong.fxml")));
                Pane searchComponent = fxmlLoader.load();

                GUIMusicResultsController searchManager = fxmlLoader.getController();
                searchManager.buildMusicLayout(trackBean, episodeNumber, seasonNumber);

                vbox.getChildren().add(searchComponent);
            }

        } catch (IOException e) {
            ExceptionSupport.showExceptionGUI("Unexpected behaviour", e.getMessage());
        }
    }

}
