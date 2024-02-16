package com.example.ispw.graphic_controller;

import com.example.ispw.bean.TvSeriesBean;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class GUIThirdTypeBannerController {

    @FXML
    private ImageView image;

    public void buildThirdLayout(TvSeriesBean tvSeriesBean) {
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tvSeriesBean.getImage())));
        image.setImage(img);
    }
}
