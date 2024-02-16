package com.example.ispw.bean;


import java.time.LocalDate;

public class StatusBean {

    private String name;
    private String status;
    private LocalDate nextAiringDate;

    public StatusBean(String name, String status){
        this.name = name;
        this.status = status;
    }

    public StatusBean(String name, String status, LocalDate nextAiringDate){
        this.name = name;
        this.status = status;
        this.nextAiringDate = nextAiringDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getNextAiringDate() {
        return nextAiringDate;
    }

    public void setNextAiringDate(LocalDate nextAiringDate) {
        this.nextAiringDate = nextAiringDate;
    }
}
