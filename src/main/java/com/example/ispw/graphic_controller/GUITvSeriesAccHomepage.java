package com.example.ispw.graphic_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GUITvSeriesAccHomepage {

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void showNewsSection(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TvSeriesAccNews.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void logout() throws IOException {
        GUILogoutController logoutGUIController = new GUILogoutController();
        logoutGUIController.logout();
    }

    public void showMusicSection(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Music.fxml"));
        AnchorPane screen = fxmlLoader.load();
        scene = new Scene(screen);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        GUIManageMusicController guiManageMusicController = fxmlLoader.getController();
        guiManageMusicController.initialize(screen);
        stage.setScene(scene);
        stage.show();
    }


}
