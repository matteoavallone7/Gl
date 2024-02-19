package com.example.ispw.controller;

import com.example.ispw.bean.LoginCredentialsBean;
import com.example.ispw.bean.SeriesOffAccountBean;
import com.example.ispw.bean.ViewerBean;
import com.example.ispw.dao.LoginDAO;
import com.example.ispw.dao.SeriesOffAccountDAO;
import com.example.ispw.dao.ViewerDAO;
import com.example.ispw.exceptions.InvalidUserCredentialsException;
import com.example.ispw.model.SeriesOffAccount;
import com.example.ispw.model.UserProfile;
import com.example.ispw.model.Viewer;
import com.example.ispw.patterns.LoginDAOFactory;
import com.example.ispw.session.Session;


public class LoginController {

    public void checkUser(LoginCredentialsBean loginCredentialsBean) {

        LoginDAO loginDAO = LoginDAOFactory.getInstance().createLoginDAO();

        UserProfile userProfile = loginDAO.findUser(loginCredentialsBean.getUsername(), loginCredentialsBean.getPassword());
        loginCredentialsBean.setRole(userProfile.getRole());
    }

    public void completeViewerLogin(LoginCredentialsBean loginCredentialsBean) throws InvalidUserCredentialsException {

        Viewer viewer = ViewerDAO.fetchViewerByUsername(loginCredentialsBean.getUsername());
        ViewerBean viewerBean = new ViewerBean(viewer.getName(), viewer.getUsername(), viewer.getEmail());
        Session.setSessionInstance(viewerBean);
    }

    public void completeSeriesOffAccountLogin(LoginCredentialsBean loginCredentialsBean) {

        SeriesOffAccount seriesOffAccount = SeriesOffAccountDAO.fetchSeriesAccountByUsername(loginCredentialsBean.getUsername());
        SeriesOffAccountBean seriesOffAccountBean = new SeriesOffAccountBean(seriesOffAccount.getSeriesName(), seriesOffAccount.getUsername(), seriesOffAccount.getEmail(), seriesOffAccount.getVerificationId());
        Session.setSessionInstance(seriesOffAccountBean);
    }

}