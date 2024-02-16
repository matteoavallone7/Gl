package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.bean.EpisodeBean;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.controller.DeleteSeriesController;
import com.example.ispw.patterns.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GUIFirstTypeBannerController {

    @FXML
    private Label epTitle;
    @FXML
    private ImageView image;
    @FXML
    private Label nameLbl;
    @FXML
    private Label sznepLbl;
    @FXML
    private Label timeslot;

    private EpisodeBean episodeBean;
    private TvSeriesBean tvSeriesBean;
    private Observer previousPage;

    private Parent pageContainer;
    private Parent banner;

    public void setBanner(Parent banner) {
        this.banner = banner;
    }

    public void setCurrentPage(Parent previousPage) {
        this.pageContainer = previousPage;
    }

    public void setPreviousPage(Observer previousPage) {
        this.previousPage = previousPage;
    }

    public void buildFirstLayout(TvSeriesBean tvSeriesBean, EpisodeBean episodeBean) {
        this.episodeBean = episodeBean;
        this.tvSeriesBean = tvSeriesBean;
        epTitle.setText(episodeBean.getTitle());
        nameLbl.setText(tvSeriesBean.getName());
        sznepLbl.setText("S" + episodeBean.getSeason() + "E" + episodeBean.getId());
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tvSeriesBean.getImage())));
        image.setImage(image1);
        if (episodeBean.getTimeslot() != null) {
            timeslot.setText(episodeBean.getTimeslot());
            timeslot.setVisible(true);
            timeslot.getParent().setStyle("-fx-background-color: #90EE90");
        } else {
            timeslot.setVisible(false);
        }
    }

    public void delete(ActionEvent event) {
        DeleteSeriesController deleteSeriesController = new DeleteSeriesController();
        AnchorPane banner = (AnchorPane) ((Button) event.getSource()).getParent();
        deleteSeriesController.deleteSeries(tvSeriesBean, banner, previousPage);
    }

    public void expandEpDetails() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EpisodeDetails.fxml"));
        AnchorPane epDetails = fxmlLoader.load();
        GUIEpisodeDetailsController guiEpisodeDetailsController = fxmlLoader.getController();
        guiEpisodeDetailsController.setObserver(previousPage);
        guiEpisodeDetailsController.setCurrentPage(pageContainer);
        guiEpisodeDetailsController.setPreviousPage(epDetails);
        guiEpisodeDetailsController.setBanner(banner);
        guiEpisodeDetailsController.buildEpisodeData(episodeBean, nameLbl.getText());

        Stage stage = Main.getStage();
        Scene scene = new Scene(epDetails);
        stage.setScene(scene);
        stage.show();
    }


}
