package com.example.ispw.controller;

import com.example.ispw.bean.PostBean;
import com.example.ispw.dao.NewsDAO;
import com.example.ispw.exceptions.NoNewsException;
import com.example.ispw.model.Post;

import java.util.ArrayList;
import java.util.List;

public class NewsController {

    public List<PostBean> fetchNews() throws NoNewsException {

        List<Post> postList;
        List<PostBean> posts = new ArrayList<>();

        postList = NewsDAO.findLatestNews();
        for (Post post : postList) {
            PostBean postBean = new PostBean(post.getAuthor(), post.getTitle(), post.getDescription(), post.getImageSource(), post.getPubDate());
            posts.add(postBean);
        }

        return posts;

    }

    public void postUpdate(PostBean postBean) {

        Post post = new Post(postBean.getAuthor(), postBean.getTitle(), postBean.getDescription(), postBean.getImageSource());
        NewsDAO.postUpdate(post);
    }


}
