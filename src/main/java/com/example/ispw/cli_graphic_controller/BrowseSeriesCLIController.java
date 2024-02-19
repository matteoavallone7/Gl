package com.example.ispw.cli_graphic_controller;

import com.example.ispw.bean.SearchBean;
import com.example.ispw.bean.TvSeriesBean;
import com.example.ispw.cli_view.BrowseSeriesViewCLI;
import com.example.ispw.controller.BrowseSeriesController;
import com.example.ispw.exceptions.*;
import com.example.ispw.utilities.ExceptionSupport;
import com.example.ispw.utilities.Printer;

import java.util.ArrayList;
import java.util.List;


public class BrowseSeriesCLIController implements GraphicCLIController {

    private BrowseSeriesViewCLI browseSeriesViewCLI;
    private static final String BROWSE = "1";
    private static final String ADD_SERIES = "2";
    private static final String RETURN_TO_HOMEPAGE = "3";

    @Override
    public void start() {
        this.browseSeriesViewCLI = new BrowseSeriesViewCLI(this);
        this.browseSeriesViewCLI.showMenu();
    }

    public void executeCommand(String inputLine) throws InvalidFormatException, SeriesNotFoundException, SessionUserException {
        switch (inputLine) {
            case BROWSE, ADD_SERIES -> this.browseSeriesViewCLI.getSeriesName();
            case RETURN_TO_HOMEPAGE -> new HomepageCLIController(1).start();
            default -> throw new InvalidFormatException("Invalid choice");
        }
    }

    public void searchSeries(String seriesName) throws InvalidFormatException, SeriesNotFoundException {
        SearchBean searchBean = new SearchBean();
        searchBean.setName(seriesName);
        List<TvSeriesBean> tvSeriesBeanList = new ArrayList<>();
        BrowseSeriesController browseSeriesController = new BrowseSeriesController();
        try {
            tvSeriesBeanList = browseSeriesController.searchSeries(searchBean);
        } catch (SeriesNotFoundException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
        }
        for (TvSeriesBean tvSeriesBean : tvSeriesBeanList) {
            TvSeriesBean tvSeriesBean1 = browseSeriesController.expandedDetails(tvSeriesBean.getName());
            BrowseSeriesViewCLI browseSeriesViewCLI1 = new BrowseSeriesViewCLI(this);
            browseSeriesViewCLI1.printResults(tvSeriesBean1.getName(), tvSeriesBean1.getSeasons(), tvSeriesBean1.getPlot(), tvSeriesBean1.getEpisodes(), tvSeriesBean1.getGenre(), tvSeriesBean1.getCountryOfOrigin(), tvSeriesBean1.getRating());
        }


        Printer.print("Want to add any of the shows to your watchlist? (y/n)\n");
        String choice = Printer.getChoice();
        if (choice.equals("y")) {
            Printer.print("Please, insert a valid name\n");
            String name = Printer.getChoice();
            AddSeriesCLIController addSeriesCLIController = new AddSeriesCLIController();
            addSeriesCLIController.checkOccurrence(name, tvSeriesBeanList);
        } else if (choice.equals("n")) {
            start();
        } else {
            throw new InvalidFormatException("Two possible answers: 'y' or 'n'.\n");
        }
    }
}
