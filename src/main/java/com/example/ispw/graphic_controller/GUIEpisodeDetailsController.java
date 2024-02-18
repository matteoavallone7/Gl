package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.bean.*;
import com.example.ispw.controller.DisplayWatchlistController;
import com.example.ispw.controller.MarkEpisodeController;
import com.example.ispw.controller.RequestController;
import com.example.ispw.patterns.Observer;
import com.example.ispw.utilities.DateSupport;
import com.example.ispw.utilities.ExceptionSupport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GUIEpisodeDetailsController {

    @FXML
    private Label airingDate;

    @FXML
    private Label idLbl;

    @FXML
    private Label rating;


    @FXML
    private TextArea textArea;

    @FXML
    private Label titleLbl;

    @FXML
    private Label watchedLbl;

    @FXML
    private VBox vBox;
    @FXML
    private ImageView epImage;
    private EpisodeBean episodeBean;
    private Observer observer;

    private Parent pageContainer;
    private Parent currentPage;
    private EpisodeBean currentEpisode;

    private Parent banner;

    public void setBanner(Parent banner) {
        this.banner = banner;
    }

    public void setPreviousPage(Parent currentPage) {
        this.currentPage = currentPage;
    }

    public void setCurrentPage(Parent previousPage) {
        this.pageContainer = previousPage;
    }


    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void buildEpisodeData(EpisodeBean episodeBean, String series) {
        DisplayWatchlistController displayWatchlistController = new DisplayWatchlistController();
        this.episodeBean = episodeBean;
        this.currentEpisode = new EpisodeBean(episodeBean.getId(), episodeBean.getSeason(), series);
        EpisodeBean episode = displayWatchlistController.getEpisodeDetails(currentEpisode);
        airingDate.setText(DateSupport.fromLocalDateToString(episode.getAiringDate()));
        rating.setText(String.valueOf(episode.getRating()));
        textArea.setText(episode.getOverview());
        titleLbl.setText(episode.getTitle());
        String id = "S" + episode.getSeason() + "E" + episode.getId();
        idLbl.setText(id);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(episode.getImgSrc())));
        epImage.setImage(image);
        List<TrackBean> trackBeanList = displayWatchlistController.getEpisodeMusicInfo(currentEpisode);
        setSongs(trackBeanList);
    }

    public void setSongs(List<TrackBean> trackBeanList) {

        try {

            for (TrackBean trackBean : trackBeanList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Objects.requireNonNull(Main.class.getResource("/Track.fxml")));
                Pane pane = fxmlLoader.load();

                GUITrackManager guiTrackManager = fxmlLoader.getController();
                guiTrackManager.buildTrackInfo(trackBean);

                vBox.getChildren().add(pane);
            }


        } catch (IOException e) {
            ExceptionSupport.showExceptionGUI("Unexpected behaviour", e.getMessage());
        }

    }

    public void leaveReview() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Review.fxml"));
        AnchorPane screen = fxmlLoader.load();
        GUIReviewController guiReviewController = fxmlLoader.getController();
        guiReviewController.setPreviousPage(currentPage);
        guiReviewController.init(currentEpisode.getId(), currentEpisode.getSeason(), currentEpisode.getTvSeries());

        Stage stage = Main.getStage();
        Scene scene = new Scene(screen);
        stage.setScene(scene);
        stage.show();
    }

    public void request() {
        RequestController requestController = new RequestController();
        RequestBean requestBean = new RequestBean();
        requestBean.setRequest("Episode " + episodeBean.getId() + " of season " + episodeBean.getSeason() + " lacks music info");
        requestBean.setSeries(currentEpisode.getTvSeries());
        requestController.requestFurtherInspection(requestBean);
    }

    public void markAsWatched() {
        MarkEpisodeController markEpisodeController = new MarkEpisodeController();
        markEpisodeController.markEpAsFullyWatched(currentEpisode, banner, observer);
    }

    public void markAsPartiallyWatched() throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Timeslot.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUITimeslotController controller = fxmlLoader.getController();
        controller.setInfo(currentEpisode.getId(), currentEpisode.getSeason(), currentEpisode.getTvSeries());
        controller.setObserver(observer);
        controller.setBanner(banner);

        dialog.setScene(scene);
        dialog.show();
    }

    public void hidePage(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = this.pageContainer.getScene();
        stage.setScene(scene);
    }


}
