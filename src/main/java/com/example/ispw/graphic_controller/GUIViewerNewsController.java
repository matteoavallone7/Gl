package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.bean.PostBean;
import com.example.ispw.controller.NewsController;
import com.example.ispw.exceptions.NoNewsException;
import com.example.ispw.model.Post;
import com.example.ispw.utilities.ExceptionSupport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GUIViewerNewsController {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;

    public void showNews() {
        NewsController newsController = new NewsController();
        List<PostBean> postBeanList;
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        try {
            postBeanList = newsController.fetchNews();
            // generate a layout pass on the scroll pane.
            scrollPane.applyCss();
            scrollPane.layout();

            // scroll to the bottom of the scroll pane.
            scrollPane.setVvalue(scrollPane.getVmax());
            setNews(postBeanList);
        } catch (NoNewsException e) {
            ExceptionSupport.showExceptionGUI("Error", e.getMessage());
        }
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

    public void showWatchlist() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Watchlist.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        GUIWatchlistController guiWatchlistController = fxmlLoader.getController();
        guiWatchlistController.displayWatchlist();
        stage.show();
    }

    public void toHomepage(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ViewerHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIViewerHomepage guiViewerHomepage = fxmlLoader.getController();
        guiViewerHomepage.initialize();
        stage.setScene(scene);
        stage.show();
    }

    public void setNews(List<PostBean> postBeanList) {

        try {

            for (PostBean postBean: postBeanList) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Post.fxml"));
                Pane post = fxmlLoader.load();

                GUINewsManager guiNewsManager = fxmlLoader.getController();
                guiNewsManager.buildNewsFeed(postBean);

                vBox.getChildren().add(post);
            }

        } catch (IOException e) {
            ExceptionSupport.showExceptionGUI("Unexpected behaviour", e.getMessage());
        }

    }
}
