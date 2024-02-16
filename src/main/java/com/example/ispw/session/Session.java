package com.example.ispw.session;

import com.example.ispw.bean.SeriesOffAccountBean;
import com.example.ispw.bean.ViewerBean;

public class Session {

    private static Session sessionInstance = null;

    private ViewerBean viewerBean;
    private SeriesOffAccountBean seriesOffAccountBean;


    private Session(Object ob) {

        if(ob instanceof ViewerBean) {
            this.viewerBean = (ViewerBean) ob;
        }
        else if(ob instanceof SeriesOffAccountBean) {
            this.seriesOffAccountBean = (SeriesOffAccountBean) ob;
        }
    }

    public static void setSessionInstance(Object ob) {
        if(sessionInstance == null)
            sessionInstance = new Session(ob);
    }

    public static void closeSession() {
        sessionInstance = null;
    }

    public static Session getCurrentSession() {
        return sessionInstance;
    }

    public ViewerBean getViewerBean() {
        return viewerBean;
    }

    public SeriesOffAccountBean getSeriesOffAccountBean() {
        return seriesOffAccountBean;
    }

}
