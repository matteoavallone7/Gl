package com.example.ispw.controller;

import com.example.ispw.bean.RequestBean;
import com.example.ispw.dao.RequestDAO;
import com.example.ispw.dao.ReviewDAO;
import com.example.ispw.dao.SeriesDAO;
import com.example.ispw.model.Request;
import com.example.ispw.session.Session;

import java.util.ArrayList;
import java.util.List;

public class RequestController {

    public void requestFurtherInspection(RequestBean requestBean) {
        Request request = new Request(requestBean.getSeries(), requestBean.getRequest());
        String viewer = Session.getCurrentSession().getViewerBean().getUsername();
        RequestDAO.makeRequest(request, viewer);
    }

    public List<RequestBean> getRequests(String series) {
        List<Request> requests;
        List<RequestBean> requestBeans = new ArrayList<>();

        requests = RequestDAO.getUserRequest(series);
        for (Request request : requests) {
            RequestBean requestBean = new RequestBean(request.getDescription(), request.getFrequency());
            requestBeans.add(requestBean);
        }

        return requestBeans;
    }

    public void changeRequestStatus(String description) {
        Request request = new Request(description);
        RequestDAO.changeStatus(request);
    }
}
