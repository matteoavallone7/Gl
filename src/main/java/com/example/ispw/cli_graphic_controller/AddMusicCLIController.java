package com.example.ispw.cli_graphic_controller;


import com.example.ispw.bean.RequestBean;
import com.example.ispw.bean.TrackBean;
import com.example.ispw.cli_view.AddMusicViewCLI;
import com.example.ispw.controller.AddMusicController;
import com.example.ispw.controller.RequestController;
import com.example.ispw.exceptions.EpisodeException;
import com.example.ispw.exceptions.InvalidFormatException;
import com.example.ispw.exceptions.SessionUserException;
import com.example.ispw.session.Session;

import java.util.List;

public class AddMusicCLIController implements GraphicCLIController {

    private AddMusicViewCLI addMusicViewCLI;

    private static final String READ_REQUESTS = "1";
    private static final String ADD_MUSIC = "2";
    private static final String RETURN_TO_HOMEPAGE = "3";

    @Override
    public void start() {
        this.addMusicViewCLI = new AddMusicViewCLI(this);
        this.addMusicViewCLI.showMenu();
    }

    public void executeCommand(String inputLine) throws SessionUserException, InvalidFormatException {
        switch (inputLine) {
            case READ_REQUESTS -> readRequests();
            case ADD_MUSIC -> this.addMusicViewCLI.getTrackInfo();
            case RETURN_TO_HOMEPAGE -> new HomepageCLIController(1).start();
            default -> throw new InvalidFormatException("Invalid choice");
        }
    }

    public void getMusicTrack(String trackName, String author, int episode, int season) throws EpisodeException {
        TrackBean trackBean = new TrackBean(author, trackName);
        AddMusicController addMusicController = new AddMusicController();
        boolean verification = addMusicController.verifyEpisode(episode, season);
        if (!verification) {
            throw new EpisodeException();
        } else {
            addMusicController.addMusicToEpisode(episode, season, trackBean);
            this.addMusicViewCLI.showMenu();
        }
    }

    public void readRequests() {
        String series = Session.getCurrentSession().getSeriesOffAccountBean().getSeriesName();
        RequestController requestController = new RequestController();
        List<RequestBean> requestBeans = requestController.getRequests(series);


        for (RequestBean requestBean : requestBeans) {
            addMusicViewCLI.printRequest(requestBean);
        }

        this.addMusicViewCLI.showMenu();
    }
}
