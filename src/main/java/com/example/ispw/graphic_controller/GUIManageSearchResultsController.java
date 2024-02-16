package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.controller.BrowseSeriesController;
import com.example.ispw.utilities.ExceptionSupport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GUIManageSearchResultsController {

    @FXML
    private Label genre;

    @FXML
    private Label rating;

    @FXML
    private ImageView seriesImg;

    @FXML
    private Label title;

    private Parent previousPage;

    public void setPreviousPage(Parent previousPage) {
        this.previousPage = previousPage;
    }

    public void buildLayout(TvSeriesBean tvSeriesBean) {
        genre.setText(tvSeriesBean.getGenre());
        rating.setText(String.valueOf(tvSeriesBean.getRating()));
        title.setText(tvSeriesBean.getName());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tvSeriesBean.getImage())));
        seriesImg.setImage(image);
    }

    public void expandDetails() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Objects.requireNonNull(Main.class.getResource("/SeriesDetails.fxml")));
            AnchorPane anchorPane = fxmlLoader.load();

            GUISeriesExpandedDetailsController seriesExpandedDetailsController = fxmlLoader.getController();
            seriesExpandedDetailsController.setPreviousPage(previousPage);
            seriesExpandedDetailsController.buildShowDetails(title.getText());

            Stage stage = Main.getStage();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            ExceptionSupport.showExceptionGUI("Unexpected behaviour", e.getMessage());
        }
    }
}
