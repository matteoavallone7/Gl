package com.example.ispw.cli_view;

import com.example.ispw.cli_graphic_controller.LoginCLIController;
import com.example.ispw.exceptions.InvalidFormatException;
import com.example.ispw.exceptions.InvalidUserCredentialsException;
import com.example.ispw.exceptions.NotYetImplementedException;
import com.example.ispw.utilities.ExceptionSupport;
import com.example.ispw.utilities.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LoginViewCLI {
    private final LoginCLIController loginCLIController;
    public LoginViewCLI(LoginCLIController currentLoginCLIController) {
        this.loginCLIController = currentLoginCLIController;
    }

    public void showMenu() {
        Printer.print("*** What should I do for you? ***\n");
        Printer.print("1) Login\n");
        Printer.print("2) Login with Google\n");
        Printer.print("3) Sign Up\n");
        Printer.print("4) Quit\n");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.loginCLIController.executeCommand(inputLine);
        } catch (InvalidFormatException | NotYetImplementedException e) {
            ExceptionSupport.showExceptionCLI(e.getMessage());
            showMenu();
        }
    }

    public void getCredentials() {
        Printer.print("username:");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        Printer.print("password:");
        String password = scanner.nextLine();

        try{
            loginCLIController.checkLogin(username, password);
        } catch (Exception e){
            Printer.printError(e.getMessage());
        }
    }
}

