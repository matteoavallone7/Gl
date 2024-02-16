package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.controller.DisplayWatchlistController;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.exceptions.DatabaseException;
import com.example.ispw.patterns.Observer;
import com.example.ispw.utilities.ExceptionSupport;
import com.example.ispw.utilities.InitializeSupport;
import com.example.ispw.utilities.Printer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class GUIWatchlistController implements Observer {

    @FXML
    private TitledPane comingSoon;

    @FXML
    private TitledPane currentlyWatching;

    @FXML
    private TitledPane finishedWatching;

    @FXML
    private GridPane grid;

    @FXML
    private TitledPane notYetStarted;

    @FXML
    private ScrollPane scrollPaneCS;

    @FXML
    private ScrollPane scrollPaneCW;

    @FXML
    private ScrollPane scrollPaneFW;

    @FXML
    private ScrollPane scrollPaneNS;

    @FXML
    private VBox vBoxCS;

    @FXML
    private VBox vBoxCW;

    @FXML
    private VBox vBoxNS;


    private int column = 0;
    private int row = 0;

    private final static String CURRENTLY_WATCHING = "CURRENTLY WATCHING";
    private final static String NOT_YET_STARTED = "NOT YET STARTED";
    private final static String COMING_SOON = "COMING SOON";
    private final static String FINISHED_WATCHING = "FINISHED WATCHING";
    private final static String JUST_DELETED = "DE";

    private Parent pageContainer;

    public void setCurrentPage(Parent previousPage) {
        this.pageContainer = previousPage;
    }

    public void toSearchSection() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SearchPage.fxml"));
        AnchorPane screen = fxmlLoader.load();
        Scene scene = new Scene(screen);
        GUISearchSeriesController guiSearchSeriesController = fxmlLoader.getController();
        guiSearchSeriesController.setCurrentPage(screen);
        stage.setScene(scene);
        stage.show();
    }

    public void toNewsSection() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/ViewerNews.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIViewerNewsController guiViewerNewsController = fxmlLoader.getController();
        guiViewerNewsController.showNews();
        stage.setScene(scene);
        stage.show();
    }

    public void toHomepage(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/ViewerHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIViewerHomepage guiViewerHomepage = fxmlLoader.getController();
        guiViewerHomepage.initialize();
        stage.setScene(scene);
        stage.show();
    }

    public void displayWatchlist() throws IOException {
        DisplayWatchlistController displayWatchlistController = new DisplayWatchlistController();
        try {
            displayWatchlistController.getWatchlistLog(this);
        } catch (DatabaseException | SQLException e) {
            ExceptionSupport.showExceptionGUI("Database Error", "Please try again later");
        } catch (DAOException e) {
            ExceptionSupport.showExceptionGUI("", e.getMessage());
        }
    }


    @Override
    public void update(Object object, String status) {
        if (status.equals(CURRENTLY_WATCHING)) {
            AnchorPane anchorPane = InitializeSupport.initBanner1(((TvSeriesBean)object), this, pageContainer);
            vBoxCW.getChildren().add(anchorPane);
        } else if (status.equals(NOT_YET_STARTED)) {
            AnchorPane anchorPane = InitializeSupport.initBanner1(((TvSeriesBean)object), this, pageContainer);
            vBoxNS.getChildren().add(anchorPane);
        } else if (status.equals(COMING_SOON)) {
            AnchorPane anchorPane = InitializeSupport.initBanner2(((TvSeriesBean)object));
            vBoxCS.getChildren().add(anchorPane);
        } else if (status.equals(FINISHED_WATCHING)) {
            AnchorPane anchorPane = InitializeSupport.initBanner3(object);
            if (column == 2) {
                row++;
                column = 0;
            }
            grid.add(anchorPane, column++, row);
        } else if (status.equals(JUST_DELETED)){
            if (vBoxCW.getChildren().contains((AnchorPane)object)) {
                vBoxCW.getChildren().remove((AnchorPane)object);
            } else {
                vBoxNS.getChildren().remove((AnchorPane) object);
            }

        } else if (status.equals("Partially watched")){
            ((AnchorPane)object).setStyle("-fx-background-color: #90EE90");
        } else {
            // Do nothing...
        }
    }

    @Override
    public void updateRealTime(TvSeriesBean tvSeriesBean, Object object, int changeList) {
        if (vBoxCW.getChildren().contains((AnchorPane)object)) {
            vBoxCW.getChildren().remove((AnchorPane)object);
        } else {
            vBoxNS.getChildren().remove((AnchorPane) object);
        }

        DisplayWatchlistController displayWatchlistController = new DisplayWatchlistController();
        TvSeriesBean tvSeries = displayWatchlistController.getTvSeriesDet(tvSeriesBean);

        if (changeList == 1) {
            AnchorPane anchorPane = InitializeSupport.initBanner1(tvSeries, this, pageContainer);
            vBoxCW.getChildren().add(anchorPane);
        } else if (changeList == 2) {
            AnchorPane anchorPane = InitializeSupport.initBanner1(tvSeries, this, pageContainer);
            vBoxNS.getChildren().add(anchorPane);
        } else if (changeList == 3) {
            AnchorPane anchorPane = InitializeSupport.initBanner2(tvSeries);
            vBoxCS.getChildren().add(anchorPane);
        } else if (changeList == 4) {
            AnchorPane anchorPane = InitializeSupport.initBanner3(tvSeries);
            if (column == 2) {
                column = 0;
                row++;
            }
            grid.add(anchorPane, column++, row);
        }
    }
}
