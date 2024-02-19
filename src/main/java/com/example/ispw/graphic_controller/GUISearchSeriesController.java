package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.bean.SearchBean;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.controller.BrowseSeriesController;
import com.example.ispw.exceptions.DAOException;
import com.example.ispw.exceptions.SeriesNotFoundException;
import com.example.ispw.utilities.ExceptionSupport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GUISearchSeriesController {


    @FXML
    private TextField searchText;

    @FXML
    private VBox vbox;

    private Parent pageContainer;

    public void setCurrentPage(Parent previousPage) {
        this.pageContainer = previousPage;
    }

    public void showNewsSection() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ViewerNews.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIViewerNewsController guiViewerNewsController = fxmlLoader.getController();
        guiViewerNewsController.showNews();
        stage.setScene(scene);
        stage.show();
    }

    public void showWatchlist() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Watchlist.fxml"));
        AnchorPane screen = fxmlLoader.load();
        Scene scene = new Scene(screen);
        GUIWatchlistController guiWatchlistController = fxmlLoader.getController();
        guiWatchlistController.setCurrentPage(screen);
        guiWatchlistController.displayWatchlist();
        stage.setScene(scene);
        stage.show();
    }

    public void showHomepage() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ViewerHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIViewerHomepage guiViewerHomepage = fxmlLoader.getController();
        guiViewerHomepage.initialize();
        stage.setScene(scene);
        stage.show();
    }



    public void searchSeries() {

        SearchBean searchBean = new SearchBean();
        searchBean.setName(searchText.getText());
        BrowseSeriesController browseSeriesController = new BrowseSeriesController();
        try {

            List<TvSeriesBean> tvSeriesBeanList = browseSeriesController.searchSeries(searchBean);
            setData(tvSeriesBeanList);

        } catch (SeriesNotFoundException e) {
            ExceptionSupport.showExceptionGUI("Search error", e.getMessage());
        }

    }

    public void setData(List<TvSeriesBean> tvSeriesBeanList) {

        vbox.getChildren().clear(); // Clear existing content
        try {
            for (TvSeriesBean tvSeriesBean: tvSeriesBeanList) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SearchedShow.fxml"));
                Pane searchComponent = fxmlLoader.load();

                GUIManageSearchResultsController searchManager = fxmlLoader.getController();
                searchManager.setPreviousPage(pageContainer);
                searchManager.buildLayout(tvSeriesBean);

                vbox.getChildren().add(searchComponent);
            }

        } catch (IOException e) {
            ExceptionSupport.showExceptionGUI("Unexpected behaviour", e.getMessage());
        }

    }


}
