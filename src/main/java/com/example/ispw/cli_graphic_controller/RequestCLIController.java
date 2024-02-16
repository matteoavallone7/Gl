package com.example.ispw.cli_graphic_controller;

import com.example.ispw.bean.EpisodeBean;
import com.example.ispw.bean.RequestBean;
import com.example.ispw.cli_view.AddMusicViewCLI;
import com.example.ispw.controller.RequestController;
import com.example.ispw.session.Session;
import com.example.ispw.utilities.Printer;

import java.util.List;

public class RequestCLIController {

    public void buildRequest(EpisodeBean episodeBean) {

        RequestBean requestBean = new RequestBean();
        requestBean.setSeries(episodeBean.getTvSeries());
        requestBean.setRequest("Episode " + episodeBean.getId() + " of season " + episodeBean.getSeason() + " lacks music info");
        RequestController requestController = new RequestController();
        requestController.requestFurtherInspection(requestBean);

    }

}
