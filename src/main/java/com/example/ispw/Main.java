package com.example.ispw;

import com.example.ispw.cli_graphic_controller.LoginCLIController;
import com.example.ispw.connection.DBConnection;
import com.example.ispw.utilities.Printer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setStage(stage);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws SQLException {
        DBConnection.closeConnection();
    }

    public static void main(String[] args) {

        DBConnection.getConnection();
        Scanner reader = new Scanner(System.in);
        int choice;
        Printer.print("Please select the desired interface:\n1. Graphic interface\n2. Command-Line interface\n");

        while(true){
            choice = reader.nextInt();
            if (choice == 1){
                launch();
                break;
            } else if (choice == 2){
                LoginCLIController loginCLIController = new LoginCLIController();
                loginCLIController.start();
            } else {
                Printer.printError("Invalid choice, please retry.");
            }
        }

    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}