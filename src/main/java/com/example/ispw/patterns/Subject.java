package com.example.ispw.patterns;

import com.example.ispw.bean.TvSeriesBean;

import java.util.List;
import java.util.Vector;

public abstract class Subject {
    private final List<Observer> observersList;

    protected Subject() {
        this((Observer) null);
    }

    protected Subject(Observer observer){
        this(new Vector<>());
        if(observer!= null){
            this.register(observer);
        }
    }

    protected Subject(List<Observer> observersList) {
        this.observersList = observersList;
    }

    public void register(Observer observer) {
        observersList.add(observer);
    }

    public void unregister(Observer observer) {
        observersList.remove(observer);
    }

    public void notifyObservers(Object object, String status){
        for (Observer observer : observersList) {
            observer.update(object, status);
        }
    }

    public void notify(TvSeriesBean tvSeriesBean, Object object, int changeList){
        for (Observer observer : observersList) {
            observer.updateRealTime(tvSeriesBean, object, changeList);
        }
    }


}
