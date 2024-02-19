package com.example.ispw.utilities;

import com.example.ispw.Main;
import com.example.ispw.graphic_controller.GUIAlertBox;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class ExceptionSupport {

    private ExceptionSupport() {}

    public static void showExceptionCLI(String message) {
        Printer.printError( "\n**************************************\n" + message + "\n\tPress ENTER to continue");
        ScannerSupport.waitEnter();
    }

    public static void showExceptionGUI(String header, String message) {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Alert.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }
        ScaleTransition st = new ScaleTransition(Duration.millis(50), root);
        st.setInterpolator(Interpolator.EASE_BOTH);
        st.setFromX(0);
        st.setFromY(0);
        st.setToX(1);
        st.setToY(1);

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setResizable(false);

        GUIAlertBox guiAlertBox = loader.getController();
        guiAlertBox.setMessage(message);
        guiAlertBox.setHeader(header);
        stage.setScene(scene);
        stage.show();
        // stage.setX(x+?) same with y ??

    }
}
