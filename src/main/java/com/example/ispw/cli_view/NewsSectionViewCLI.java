package com.example.ispw.cli_view;

import com.example.ispw.bean.PostBean;
import com.example.ispw.cli_graphic_controller.NewsSectionCLIController;
import com.example.ispw.exceptions.DatabaseException;
import com.example.ispw.exceptions.InvalidFormatException;
import com.example.ispw.exceptions.NotYetImplementedException;
import com.example.ispw.exceptions.SessionUserException;
import com.example.ispw.utilities.ExceptionSupport;
import com.example.ispw.utilities.Printer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class NewsSectionViewCLI {

    private final NewsSectionCLIController newsSectionCLIController;

    public NewsSectionViewCLI(NewsSectionCLIController newsSectionCLIController){
        this.newsSectionCLIController = newsSectionCLIController;
    }

    public void showViewerMenu() {
        Printer.print("***      VIEWER NEWS SECTION      ***\n");
        Printer.print("*** What should I do for you? ***\n");
        Printer.print("1) Read news\n");
        Printer.print("2) Comment\n");
        Printer.print("3) Return to Homepage\n");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        try {
            this.newsSectionCLIController.executeCommand(choice);
        } catch (InvalidFormatException | NotYetImplementedException | SessionUserException | SQLException |
                 DatabaseException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
            showViewerMenu();
        }
    }

    public void showSeriesOffAccountMenu() {
        Printer.print("***      SERIES ACCOUNT NEWS SECTION      ***\n");
        Printer.print("*** What should I do for you? ***\n");
        Printer.print("1) Post an update\n");
        Printer.print("2) Comment\n");
        Printer.print("3) Return to Homepage\n");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        try {
            this.newsSectionCLIController.executeCommand(choice);
        } catch (InvalidFormatException | NotYetImplementedException | SessionUserException | SQLException |
                 DatabaseException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
            showSeriesOffAccountMenu();
        }
    }

    public void printNews(String author, String description, String title, LocalDate pubDate) {
        Printer.print("-----------------------------------------------------------");
        Printer.print("[" + author + "]" + "  " + pubDate + "\n");
        Printer.print(title);
        Printer.print("\n");
        Printer.print(description);
        Printer.print("\n-----------------------------------------------------------\n");
    }
}
