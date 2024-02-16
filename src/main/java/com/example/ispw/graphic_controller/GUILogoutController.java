package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.controller.LogoutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GUILogoutController {
    public void logout() throws IOException {
        LogoutController logoutController = new LogoutController();
        logoutController.logout();

        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
