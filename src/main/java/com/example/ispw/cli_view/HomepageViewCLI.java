package com.example.ispw.cli_view;

import com.example.ispw.cli_graphic_controller.HomepageCLIController;
import com.example.ispw.exceptions.InvalidFormatException;
import com.example.ispw.exceptions.SessionUserException;
import com.example.ispw.utilities.ExceptionSupport;
import com.example.ispw.utilities.Printer;

import java.util.Scanner;

public class HomepageViewCLI {

    private final HomepageCLIController homepageCLIController;
    private static final int VIEWER = 1;
    private static final int SERIESOFFACCOUNT = 2;

    public HomepageViewCLI(HomepageCLIController homepageCLIController) {
        this.homepageCLIController = homepageCLIController;
    }


    public void selectMenu(int userRole) throws SessionUserException {
        switch (userRole) {
            case VIEWER -> showViewerMenu();
            case SERIESOFFACCOUNT -> showSeriesOffAccountMenu();
            default -> throw new SessionUserException();
        }
    }

    private void showViewerMenu() {
        Printer.print("***      VIEWER HOMEPAGE      ***\n");
        Printer.print("*** What should I do for you? ***\n");
        Printer.print("1) Update Watchlist\n");
        Printer.print("2) Browse/Add tv series\n");
        Printer.print("3) Check News\n");
        Printer.print("4) Logout\n");
        Printer.print("5) Quit\n");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.homepageCLIController.executeViewerCommand(inputLine);
        } catch (InvalidFormatException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
            showViewerMenu();
        }
    }

    private void showSeriesOffAccountMenu() {
        Printer.print("***      SERIES ACCOUNT HOMEPAGE      ***\n");
        Printer.print("*** What should I do for you? ***\n");
        Printer.print("1) Create new post\n");
        Printer.print("2) Manage episode tracks\n");
        Printer.print("3) Logout\n");
        Printer.print("4) Quit\n");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.homepageCLIController.executeSeriesCommand(inputLine);
        } catch (InvalidFormatException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
            showSeriesOffAccountMenu();
        }
    }

}
