package com.example.ispw.cli_view;

import com.example.ispw.cli_graphic_controller.BrowseSeriesCLIController;
import com.example.ispw.exceptions.DatabaseException;
import com.example.ispw.exceptions.InvalidFormatException;
import com.example.ispw.exceptions.SeriesNotFoundException;
import com.example.ispw.exceptions.SessionUserException;
import com.example.ispw.utilities.ExceptionSupport;
import com.example.ispw.utilities.Printer;

import java.sql.SQLException;
import java.util.Scanner;

public class BrowseSeriesViewCLI {

    private final BrowseSeriesCLIController browseSeriesCLIController;

    public BrowseSeriesViewCLI(BrowseSeriesCLIController browseSeriesCLIController) {
        this.browseSeriesCLIController = browseSeriesCLIController;
    }

    public void showMenu() {
        Printer.print("*** What should I do for you? ***\n");
        Printer.print("1) Browse tv series\n");
        Printer.print("2) Add tv series\n");
        Printer.print("3) Return to Homepage\n");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.browseSeriesCLIController.executeCommand(inputLine);
        } catch (InvalidFormatException | SQLException | SeriesNotFoundException | DatabaseException |
                 SessionUserException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
            showMenu();
        }
    }

    public void getSeriesName() throws SeriesNotFoundException, InvalidFormatException {
        Printer.print("Tv series name: ");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        this.browseSeriesCLIController.searchSeries(inputLine);
    }

    public void printResults(String name, int numSeasons, String plot, int numEpisodes, String genre, String countryOfOrigin, float rating) {
        Printer.print("-----------------------------------------------------------");
        Printer.print("[" + name + "]" + " rating: " + rating);
        Printer.print("\nSeasons: " + numSeasons + ", Episodes: " + numEpisodes + ", Country: " + countryOfOrigin + ", Genre: " + genre + ".\n");
        Printer.print("Plot:\n");
        Printer.print(plot);
        Printer.print("\n-----------------------------------------------------------\n");
    }

}
