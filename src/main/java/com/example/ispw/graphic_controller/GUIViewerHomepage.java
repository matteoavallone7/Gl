package com.example.ispw.graphic_controller;

import com.example.ispw.Main;
import com.example.ispw.bean.PostBean;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.controller.HomepageController;
import com.example.ispw.controller.NewsController;
import com.example.ispw.session.Session;
import com.example.ispw.utilities.DateSupport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GUIViewerHomepage {

    @FXML
    private Label date1Lbl;

    @FXML
    private Label date2Lbl;

    @FXML
    private Label date3Lbl;

    @FXML
    private Label genre1Lbl;

    @FXML
    private Label genre2Lbl;

    @FXML
    private Label genre3Lbl;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private TextField newsAuthor;

    @FXML
    private TextField newsHeader;

    @FXML
    private TextArea newsText;

    @FXML
    private Label show1Lbl;

    @FXML
    private Label show2Lbl;

    @FXML
    private Label show3Lbl;

    @FXML
    private Label welcomeLbl;

    public void initialize() {

        welcomeLbl.setText("Hey, " + Session.getCurrentSession().getViewerBean().getName() + "!");
        welcomeLbl.setMaxWidth(Double.MAX_VALUE);
        welcomeLbl.setAlignment(Pos.CENTER);

        HomepageController homepageController = new HomepageController();
        PostBean postBean = homepageController.getPostOverview();
        newsAuthor.setText(postBean.getAuthor());
        newsText.setText(postBean.getDescription());
        newsHeader.setText(postBean.getTitle());

        List<TvSeriesBean> tvSeriesBeanList = homepageController.getRandomSeries();
        show1Lbl.setText(tvSeriesBeanList.get(0).getName());
        show2Lbl.setText(tvSeriesBeanList.get(1).getName());
        show3Lbl.setText(tvSeriesBeanList.get(2).getName());
        genre1Lbl.setText(tvSeriesBeanList.get(0).getGenre());
        genre2Lbl.setText(tvSeriesBeanList.get(1).getGenre());
        genre3Lbl.setText(tvSeriesBeanList.get(2).getGenre());
        date1Lbl.setText(DateSupport.fromLocalDateToString(tvSeriesBeanList.get(0).getAiringDate()));
        date2Lbl.setText(DateSupport.fromLocalDateToString(tvSeriesBeanList.get(1).getAiringDate()));
        date3Lbl.setText(DateSupport.fromLocalDateToString(tvSeriesBeanList.get(2).getAiringDate()));
        Image image1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tvSeriesBeanList.get(0).getImage())));
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tvSeriesBeanList.get(1).getImage())));
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tvSeriesBeanList.get(2).getImage())));
        img1.setImage(image1);
        img2.setImage(image2);
        img3.setImage(image3);

    }


    public void logout() throws IOException {
        GUILogoutController logoutGUIController = new GUILogoutController();
        logoutGUIController.logout();
    }

    public void toNewsSection() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ViewerNews.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        GUIViewerNewsController guiViewerNewsController = fxmlLoader.getController();
        guiViewerNewsController.showNews();
        stage.show();
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

    public void toWatchlist() throws IOException {
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
}
