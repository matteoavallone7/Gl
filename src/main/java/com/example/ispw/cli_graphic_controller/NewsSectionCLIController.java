package com.example.ispw.cli_graphic_controller;

import com.example.ispw.bean.PostBean;
import com.example.ispw.cli_view.NewsSectionViewCLI;
import com.example.ispw.controller.NewsController;
import com.example.ispw.exceptions.*;
import com.example.ispw.session.Session;
import com.example.ispw.utilities.ExceptionSupport;

import java.sql.SQLException;
import java.util.List;

public class NewsSectionCLIController implements GraphicCLIController {

    private NewsSectionViewCLI newsSectionViewCLI;

    @Override
    public void start() {
        this.newsSectionViewCLI = new NewsSectionViewCLI(this);
        if (Session.getCurrentSession().getViewerBean() != null) {
            this.newsSectionViewCLI.showViewerMenu();
        } else {
            this.newsSectionViewCLI.showSeriesOffAccountMenu();
        }
    }

    public void executeCommand(int choice) throws NotYetImplementedException, InvalidFormatException, SessionUserException, SQLException, DatabaseException {
        switch(choice) {
            case 1 -> {
                if (Session.getCurrentSession().getViewerBean() != null) {
                    readNews();
                } else {
                    PostUpdateCLIController postUpdateCLIController = new PostUpdateCLIController();
                    postUpdateCLIController.start();
                    start();
                }
            }
            case 2 -> throw new NotYetImplementedException();
            case 3 -> manageHomepage();
            default -> throw new InvalidFormatException();
        }
    }

    public void manageHomepage() throws SessionUserException {
        if (Session.getCurrentSession().getViewerBean() != null) {
            new HomepageCLIController(1).start();
        } else {
            new HomepageCLIController(2).start();
        }
    }

    public void readNews() {
        NewsController newsController = new NewsController();
        List<PostBean> postBeanList;
        try {

            postBeanList = newsController.fetchNews();

            for (PostBean post : postBeanList) {
                newsSectionViewCLI.printNews(post.getAuthor(), post.getDescription(), post.getTitle(), post.getDate());
            }

        } catch (NoNewsException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
        }

    }
}
