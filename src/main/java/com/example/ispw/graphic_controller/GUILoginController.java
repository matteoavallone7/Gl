package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.bean.LoginCredentialsBean;
import com.example.ispw.controller.LoginController;
import com.example.ispw.exceptions.InvalidUserCredentialsException;

import com.example.ispw.exceptions.SessionUserException;
import com.example.ispw.utilities.ExceptionSupport;
import com.example.ispw.utilities.Printer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class GUILoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField textField;

    @FXML
    private Label message;
    private Stage stage;
    private Scene scene;


    public void goToViewerHomepage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ViewerHomepage.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        GUIViewerHomepage guiViewerHomepage = fxmlLoader.getController();
        guiViewerHomepage.initialize();
        System.out.println("initializing...");
        stage.show();
    }

    public void goToSeriesAccHomepage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TvSeriesAccHomepage.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void notImplemented() {
        ExceptionSupport.showExceptionGUI("Something went wrong!", "Functionality not implemented");
    }

    public void login(ActionEvent event) {

        try {
            LoginCredentialsBean loginCredentialsBean = new LoginCredentialsBean(textField.getText(), passwordField.getText());
            LoginController loginController = new LoginController();
            loginController.checkUser(loginCredentialsBean);

            if(loginCredentialsBean.getRole() == 1){
                loginController.completeViewerLogin(loginCredentialsBean);

                goToViewerHomepage(event);

            } else if (loginCredentialsBean.getRole()  == 2){
                loginController.completeSeriesOffAccountLogin(loginCredentialsBean);

                goToSeriesAccHomepage(event);

            } else {
                throw new InvalidUserCredentialsException();
            }

        } catch (SessionUserException e) {
            message.setText("User does not exist");
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        } catch (InvalidUserCredentialsException e) {
            message.setText("Invalid Credentials");
        }

    }


}
