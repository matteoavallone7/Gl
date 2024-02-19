package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.bean.PostBean;
import com.example.ispw.bean.SearchBean;
import com.example.ispw.controller.NewsController;
import com.example.ispw.exceptions.EmptyFieldException;
import com.example.ispw.model.Post;
import com.example.ispw.session.Session;
import com.example.ispw.utilities.ExceptionSupport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.channels.AcceptPendingException;
import java.util.Objects;

public class GUITvSeriesAccNewsController {

    @FXML
    private TextArea description1;

    @FXML
    private TextArea description2;

    @FXML
    private TextField title1;

    @FXML
    private TextField title2;

    @FXML
    private Label fileLbl;

    @FXML
    private Button imageBttn;
    private File file = null;

    public PostBean getDefaultPostInfo() {
        PostBean postBean = new PostBean();
        postBean.setTitle(title1.getText());
        postBean.setDescription(description1.getText());
        postBean.setImageSource("/img/default-post-image.jpg");
        postBean.setAuthor(Session.getCurrentSession().getSeriesOffAccountBean().getUsername());
        return postBean;
    }

    public PostBean getImagePostInfo() {
        PostBean imagePostBean = new PostBean();
        imagePostBean.setTitle(title2.getText());
        imagePostBean.setDescription(description2.getText());
        imagePostBean.setAuthor(Session.getCurrentSession().getSeriesOffAccountBean().getUsername());
        imagePostBean.setImageSource("/img/" + file.getName());
        return imagePostBean;
    }

    public void createDefaultPost() {
        NewsController newsController = new NewsController();
        PostBean postBean = getDefaultPostInfo();
        try {
            if (postBean.getTitle().isBlank() || postBean.getDescription().isBlank()) {
                throw new EmptyFieldException();
            }
        } catch (EmptyFieldException e) {
            ExceptionSupport.showExceptionGUI("Incomplete info", e.getMessage());
        }
        newsController.postUpdate(postBean);
    }

    public void createImagePost() {
        NewsController newsController = new NewsController();
        PostBean imagePostBean = getImagePostInfo();
        try {
            if (imagePostBean.getTitle().isBlank() || imagePostBean.getDescription().isBlank() || imagePostBean.getImageSource().isBlank()) {
                throw new EmptyFieldException();
            }
        } catch (EmptyFieldException e) {
            ExceptionSupport.showExceptionGUI("Incomplete info", e.getMessage());
        }
        newsController.postUpdate(imagePostBean);
    }

    public void loadImage(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg"));
        file = fileChooser.showOpenDialog(stage).getAbsoluteFile();
        imageBttn.setVisible(false);
        fileLbl.setVisible(true);
        fileLbl.setText(file.getName());
    }

    public void goBack() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TvSeriesAccHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

}
