package com.example.ispw.cli_graphic_controller;

import com.example.ispw.bean.LoginCredentialsBean;
import com.example.ispw.cli_view.LoginViewCLI;
import com.example.ispw.controller.LoginController;
import com.example.ispw.exceptions.*;
import com.example.ispw.utilities.ExceptionSupport;

public class LoginCLIController implements GraphicCLIController {

    private static final String LOGIN = "1";
    private static final String LOGIN_WITH_GOOGLE = "2";
    private static final String SIGN_UP = "3";
    private static final String QUIT = "4";

    private LoginViewCLI loginViewCLI;

    @Override
    public void start() {

        this.loginViewCLI = new LoginViewCLI(this);
        this.loginViewCLI.showMenu();

    }

    public void executeCommand(String inputLine) throws NotYetImplementedException, InvalidFormatException {
        switch (inputLine) {
            case LOGIN -> this.loginViewCLI.getCredentials();
            case LOGIN_WITH_GOOGLE, SIGN_UP -> throw new NotYetImplementedException();
            case QUIT -> System.exit(0);
            default -> throw new InvalidFormatException("Invalid choice");
        }
    }

    public void checkLogin (String username, String password) {
        try {
            LoginCredentialsBean loginCredentialsBean = new LoginCredentialsBean(username, password);
            LoginController loginController= new LoginController();
            loginController.checkUser(loginCredentialsBean);

            if (loginCredentialsBean.getRole() == 1) {
                loginController.completeViewerLogin(loginCredentialsBean);
            } else if (loginCredentialsBean.getRole() == 2) {
                loginController.completeSeriesOffAccountLogin(loginCredentialsBean);
            } else {
                throw new InvalidUserCredentialsException();
            }

            HomepageCLIController homepageCLIController = new HomepageCLIController(loginCredentialsBean.getRole());
            homepageCLIController.start();

        } catch (InvalidUserCredentialsException | SessionUserException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
            start();
        }

    }

}
