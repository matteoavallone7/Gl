package com.example.ispw.utilities;

import com.example.ispw.Main;
import com.example.ispw.bean.EpisodeBean;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.controller.DisplayWatchlistController;
import com.example.ispw.graphic_controller.GUIFirstTypeBannerController;
import com.example.ispw.graphic_controller.GUISecondTypeBannerController;
import com.example.ispw.graphic_controller.GUIThirdTypeBannerController;
import com.example.ispw.patterns.Observer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class InitializeSupport {

    private InitializeSupport() {}

    public static AnchorPane initBanner1(TvSeriesBean tvSeriesBean, Observer observer, Parent currentPage) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Objects.requireNonNull(Main.class.getResource("/Banner1.fxml")));
        AnchorPane anchorPane = null;
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

        GUIFirstTypeBannerController controller = fxmlLoader.getController();
        controller.setPreviousPage(observer);
        controller.setCurrentPage(currentPage);
        controller.setBanner(anchorPane);
        DisplayWatchlistController displayWatchlistController = new DisplayWatchlistController();
        EpisodeBean episodeBean = displayWatchlistController.getLatestUnseenEpisode(tvSeriesBean);
        controller.buildFirstLayout(tvSeriesBean, episodeBean);
        return anchorPane;
    }

    public static AnchorPane initBanner2(TvSeriesBean tvSeriesBean) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Objects.requireNonNull(Main.class.getResource("/Banner2.fxml")));
        AnchorPane anchorPane = null;
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }


        GUISecondTypeBannerController controller = fxmlLoader.getController();
        long daysLeft = DateSupport.daysLeft(tvSeriesBean.getAiringDate());
        controller.buildSecondLayout(tvSeriesBean, daysLeft);
        return anchorPane;
    }

    public static AnchorPane initBanner3(Object object) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Objects.requireNonNull(Main.class.getResource("/Banner3.fxml")));
        AnchorPane anchorPane = null;
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

        GUIThirdTypeBannerController controller = fxmlLoader.getController();
        controller.buildThirdLayout((TvSeriesBean) object);
        return anchorPane;
    }
}
