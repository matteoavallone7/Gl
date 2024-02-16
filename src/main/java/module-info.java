module com.example.ispw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.controlsfx.controls;


    opens com.example.ispw to javafx.fxml;
    exports com.example.ispw;
    exports com.example.ispw.graphic_controller;
    opens com.example.ispw.graphic_controller to javafx.fxml;
    exports com.example.ispw.model;
    opens com.example.ispw.model to javafx.fxml;
    exports com.example.ispw.exceptions;
    opens com.example.ispw.exceptions to javafx.fxml;
    exports com.example.ispw.bean;
    opens com.example.ispw.bean to javafx.fxml;
    exports com.example.ispw.connection;
    opens com.example.ispw.connection to javafx.fxml;
    exports com.example.ispw.utilities;
    opens com.example.ispw.utilities to javafx.fxml;
    exports com.example.ispw.patterns;
    opens com.example.ispw.patterns to javafx.fxml;
    exports com.example.ispw.enums;
    opens com.example.ispw.enums to javafx.fxml;
}