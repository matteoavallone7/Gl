package com.example.ispw.graphic_controller;

import com.example.ispw.bean.ReviewBean;
import com.example.ispw.controller.ReviewController;
import com.example.ispw.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

public class GUIReviewController {

    @FXML
    private Rating rating;

    @FXML
    private TextArea reviewText;

    private int episode;
    private int season;
    private String series;

    private Parent currentPage;

    public void setPreviousPage(Parent currentPage) {
        this.currentPage = currentPage;
    }

    public void init(int episode, int season, String series) {
        this.episode = episode;
        this.season = season;
        this.series = series;
    }

    public void buildReview() {
        ReviewBean reviewBean = new ReviewBean(episode, season, series, reviewText.getText(),
                (float) rating.getRating(), Session.getCurrentSession().getViewerBean().getUsername());
        ReviewController reviewController = new ReviewController();
        reviewController.publishReview(reviewBean);
    }

    public void hidePage(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = this.currentPage.getScene();
        stage.setScene(scene);
    }

}
