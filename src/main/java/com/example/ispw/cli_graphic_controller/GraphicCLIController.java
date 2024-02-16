package com.example.ispw.cli_graphic_controller;

import com.example.ispw.exceptions.DatabaseException;
import com.example.ispw.exceptions.InvalidFormatException;
import com.example.ispw.exceptions.RatingFormatException;
import com.example.ispw.exceptions.SessionUserException;

import java.io.IOException;
import java.sql.SQLException;

public interface GraphicCLIController {
    void start() throws InvalidFormatException, SessionUserException, RatingFormatException, IOException, SQLException, DatabaseException;
}