package com.example.ispw.cli_graphic_controller;

import com.example.ispw.bean.EpisodeBean;
import com.example.ispw.bean.ReviewBean;
import com.example.ispw.cli_view.ReviewViewCLI;
import com.example.ispw.controller.ReviewController;
import com.example.ispw.exceptions.RatingFormatException;

import java.io.IOException;

public class ReviewCLIController implements GraphicCLIController {

    private ReviewViewCLI reviewViewCLI;
    private EpisodeBean episodeBean;

    public ReviewCLIController(EpisodeBean episodeBean) {
        this.episodeBean = episodeBean;
    }


    @Override
    public void start() throws RatingFormatException, IOException {
        this.reviewViewCLI = new ReviewViewCLI(this);
        this.reviewViewCLI.leaveReview(episodeBean);
    }

    public void review(ReviewBean reviewBean) {
        ReviewController reviewController = new ReviewController();
        reviewController.publishReview(reviewBean);
    }
}
