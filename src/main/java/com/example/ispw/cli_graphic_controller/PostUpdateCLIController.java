package com.example.ispw.cli_graphic_controller;

import com.example.ispw.bean.PostBean;
import com.example.ispw.cli_view.PostUpdateViewCLI;
import com.example.ispw.controller.NewsController;
import com.example.ispw.exceptions.DatabaseException;

import java.sql.SQLException;

public class PostUpdateCLIController implements GraphicCLIController {

    private PostUpdateViewCLI postUpdateViewCLI;

    @Override
    public void start() throws SQLException, DatabaseException {
        this.postUpdateViewCLI = new PostUpdateViewCLI(this);
        this.postUpdateViewCLI.publishPost();
    }

    public void updateTimeline(String author, String title, String description, String image) {
        PostBean postBean = new PostBean();
        postBean.setAuthor(author);
        postBean.setTitle(title);
        postBean.setDescription(description);
        postBean.setImageSource(image);
        NewsController newsController = new NewsController();
        newsController.postUpdate(postBean);
    }
}
