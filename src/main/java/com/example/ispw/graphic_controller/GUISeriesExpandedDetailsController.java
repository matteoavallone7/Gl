package com.example.ispw.graphic_controller;

import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.controller.AddSeriesController;
import com.example.ispw.controller.BrowseSeriesController;
import com.example.ispw.patterns.Observer;
import com.example.ispw.utilities.DateSupport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class GUISeriesExpandedDetailsController implements Observer {

    @FXML
    private Label airingDate;

    @FXML
    private Label airingStatus;

    @FXML
    private Label country;

    @FXML
    private Label episodes;

    @FXML
    private Label genre;

    @FXML
    private ImageView imageBehind;

    @FXML
    private ImageView imageDisplay;

    @FXML
    private TextArea overview;

    @FXML
    private Label rating;

    @FXML
    private Label seasons;

    @FXML
    private Label title;

    @FXML
    private Button addBttn;

    @FXML
    private Label lbl;
    @FXML
    private AnchorPane pane;

    private Parent previousPage;

    public void setPreviousPage(Parent previousPage) {
        this.previousPage = previousPage;
    }

    public void buildShowDetails(String tvSeriesName) {
        BrowseSeriesController browseSeriesController = new BrowseSeriesController();
        TvSeriesBean tvSeriesBean = browseSeriesController.expandedDetails(tvSeriesName);
        title.setText(tvSeriesBean.getName());
        airingDate.setText(DateSupport.fromLocalDateToString(tvSeriesBean.getAiringDate()));
        airingStatus.setText(String.valueOf(tvSeriesBean.getWatchlistStatus()));
        country.setText("Country of origin: " + tvSeriesBean.getCountryOfOrigin());
        episodes.setText("Episodes: " + tvSeriesBean.getEpisodes());
        genre.setText("Genre: " + tvSeriesBean.getGenre());
        overview.setText(tvSeriesBean.getPlot());
        rating.setText(String.valueOf(tvSeriesBean.getRating()));
        seasons.setText("Seasons: " + tvSeriesBean.getSeasons());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tvSeriesBean.getImage())));
        imageBehind.setImage(image);
        imageDisplay.setImage(image);
    }

    public void hidePage(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = this.previousPage.getScene();
        stage.setScene(scene);
    }

    public void addToWatchlist() {
        AddSeriesController addSeriesController = new AddSeriesController();
        TvSeriesBean tvSeriesBean = new TvSeriesBean(title.getText());
        addSeriesController.addSeries(tvSeriesBean, this);
    }

    @Override
    public void update(Object object, String status) {
        addBttn.setVisible(false);
        lbl.setVisible(true);
    }

    @Override
    public void updateRealTime(TvSeriesBean tvSeriesBean, Object object, int changeList) {
        // Do nothing...
    }
}
