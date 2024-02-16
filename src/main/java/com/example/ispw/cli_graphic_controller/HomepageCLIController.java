package com.example.ispw.cli_graphic_controller;

import com.example.ispw.bean.LoginCredentialsBean;
import com.example.ispw.cli_view.HomepageViewCLI;
import com.example.ispw.controller.LogoutController;
import com.example.ispw.exceptions.InvalidFormatException;
import com.example.ispw.exceptions.NotYetImplementedException;
import com.example.ispw.exceptions.SessionUserException;

public class HomepageCLIController implements GraphicCLIController {

    private HomepageViewCLI homepageViewCLI;
    private int role;


    public HomepageCLIController(int userRole) {
        this.role = userRole;
    }

    @Override
    public void start() throws SessionUserException {
        this.homepageViewCLI = new HomepageViewCLI(this);
        this.homepageViewCLI.selectMenu(role);
    }

    public void executeViewerCommand(String inputLine) throws InvalidFormatException {
          switch (inputLine) {
              case "1" -> {
                  WatchlistCLIController watchlistCLIController = new WatchlistCLIController();
                  watchlistCLIController.start();
              }
              case "2" -> {
                  BrowseSeriesCLIController browseSeriesCLIController = new BrowseSeriesCLIController();
                  browseSeriesCLIController.start();
              }
              case "3" -> {
                  NewsSectionCLIController newsSectionCLIController = new NewsSectionCLIController();
                  newsSectionCLIController.start();
              }
              case "4" -> {
                  LogoutController logoutController = new LogoutController();
                  logoutController.logout();
                  LoginCLIController loginCLIController = new LoginCLIController();
                  loginCLIController.start();
              }
              case "5" -> System.exit(0);
              default -> throw new InvalidFormatException();
          }
    }

    public void executeSeriesCommand(String inputLine) throws InvalidFormatException {
        switch (inputLine) {
            case "1" -> {
                NewsSectionCLIController newsSectionCLIController = new NewsSectionCLIController();
                newsSectionCLIController.start();
            }
            case "2" -> {
                AddMusicCLIController addMusicCLIController = new AddMusicCLIController();
                addMusicCLIController.start();
            }
            case "3" -> {
                LogoutController logoutController = new LogoutController();
                logoutController.logout();
                LoginCLIController loginCLIController = new LoginCLIController();
                loginCLIController.start();
            }
            case "4" -> System.exit(0);
            default -> throw new InvalidFormatException();
        }
    }


}
