package com.example.ispw.dao;

import com.example.ispw.exceptions.InvalidUserCredentialsException;
import com.example.ispw.model.UserProfile;
import com.example.ispw.utilities.Printer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginDAOCSV implements LoginDAO {

    private static final String CSV_FILE_NAME = "src/main/resources/Users.csv";
    private static final int USERNAME = 0;
    private static final int PASSWORD = 1;
    private static final int ROLE = 2;
    @Override
    public UserProfile findUser(String username, String password) {

        int role;
        UserProfile userProfile = null;
        File file = new File(CSV_FILE_NAME);

        //closing the resource bufferedReader is handled automatically by the try-with-resources
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String row;
            String[] data;

            while((row = bufferedReader.readLine()) != null){
                data = row.split(",");
                if(data[USERNAME].equals(username) && data[PASSWORD].equals(password)){
                    switch(data[ROLE]){
                        case "viewer" -> role = 1;
                        case "off_account" -> role = 2;
                        default -> throw new InvalidUserCredentialsException();
                    }
                    userProfile = new UserProfile(username, role);
                    break;
                }
            }

            if(userProfile == null){
                throw new InvalidUserCredentialsException();
            }

        }catch (IOException | InvalidUserCredentialsException e) {
            Printer.printError(e.getMessage());
        }

        return userProfile;
    }

}
