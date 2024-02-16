package com.example.ispw.graphic_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class GUIAlertBox {

    @FXML
    private Label errorLbl;
    @FXML
    private Label msgLbl;

    public void setHeader(String header) {
        this.errorLbl.setText(header);
    }

    public void setMessage(String message) {
        this.msgLbl.setText(message);
    }

    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
