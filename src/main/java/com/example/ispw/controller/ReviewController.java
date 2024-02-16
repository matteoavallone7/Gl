package com.example.ispw.controller;

import com.example.ispw.bean.ReviewBean;
import com.example.ispw.dao.ReviewDAO;
import com.example.ispw.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewController {

    public void publishReview(ReviewBean reviewBean) {

        Review review = new Review(reviewBean.getId(), reviewBean.getSeason(), reviewBean.getTvSeries(), reviewBean.getReviewText(), reviewBean.getReviewRating(), reviewBean.getUsername());
        ReviewDAO.postReview(review);
    }

}
