package com.example.ispw.graphic_controller;

import com.example.ispw.bean.EpisodeBean;
import com.example.ispw.bean.TvSeriesBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class GUISecondTypeBannerController {

    @FXML
    private Label daysLeft;
    @FXML
    private ImageView image;
    @FXML
    private Label nameLbl;

    public void buildSecondLayout(TvSeriesBean tvSeriesBean, long days) {
        daysLeft.setText(String.valueOf(days));
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tvSeriesBean.getImage())));
        image.setImage(img);
        nameLbl.setText(tvSeriesBean.getName());
    }
}
