package com.example.ispw.cli_view;

import com.example.ispw.bean.EpisodeBean;
import com.example.ispw.bean.ReviewBean;
import com.example.ispw.cli_graphic_controller.ReviewCLIController;
import com.example.ispw.exceptions.RatingFormatException;
import com.example.ispw.session.Session;
import com.example.ispw.utilities.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReviewViewCLI {

    private final ReviewCLIController reviewCLIController;

    public ReviewViewCLI(ReviewCLIController reviewCLIController) {
        this.reviewCLIController = reviewCLIController;
    }

    public void leaveReview(EpisodeBean episodeBean) throws IOException, RatingFormatException {
        Printer.print("You're leaving a review of episode " + episodeBean.getId() + ", season " + episodeBean.getSeason() +
                " of the show " + episodeBean.getTvSeries() + ".\n");
        Printer.print("Please write your review: \n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        String review = reader.readLine();
        Printer.print("Leave a rating from 1 to 10: \n");
        float rating = scanner.nextFloat();
        if (rating < 1 || rating > 10) {
            throw new RatingFormatException();
        }
        String username = Session.getCurrentSession().getViewerBean().getUsername();
        ReviewBean reviewBean = new ReviewBean(episodeBean.getId(), episodeBean.getSeason(), episodeBean.getTvSeries(), review, rating, username);
        this.reviewCLIController.review(reviewBean);
    }
}
