package com.example.ispw.cli_view;

import com.example.ispw.bean.RequestBean;
import com.example.ispw.cli_graphic_controller.AddMusicCLIController;
import com.example.ispw.exceptions.EpisodeException;
import com.example.ispw.exceptions.InvalidFormatException;
import com.example.ispw.exceptions.InvalidUserCredentialsException;
import com.example.ispw.exceptions.SessionUserException;
import com.example.ispw.utilities.ExceptionSupport;
import com.example.ispw.utilities.Printer;

import java.util.Scanner;

public class AddMusicViewCLI {

    private final AddMusicCLIController addMusicCLIController;

    public AddMusicViewCLI(AddMusicCLIController addMusicCLIController) {
        this.addMusicCLIController = addMusicCLIController;
    }

    public void showMenu() {
        Printer.print("*** What should I do for you? ***\n");
        Printer.print("1) Read requests\n");
        Printer.print("2) Add music\n");
        Printer.print("3) Return to Homepage\n");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.addMusicCLIController.executeCommand(inputLine);
        } catch (SessionUserException | InvalidFormatException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
            showMenu();
        }
    }

    public void getTrackInfo() {
        Scanner input = new Scanner(System.in);
        Printer.print("Type in the title of the track you'd like to add\n");
        String title = input.nextLine();
        Printer.print("Type in the author\n");
        String author = input.nextLine();
        Printer.print("In which episode would you like this song to be featured in?\n");
        int episode = input.nextInt();
        Printer.print("Which season?\n");
        int season = input.nextInt();
        try {
            this.addMusicCLIController.getMusicTrack(title, author, episode, season);
        } catch (EpisodeException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
            getTrackInfo();
        }
    }

    public void printRequest(RequestBean requestBean) {
        Printer.print("You received " + requestBean.getFrequency() + " requests with the following description: " + requestBean.getRequest() + "\n");
    }
}
