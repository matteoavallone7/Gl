package com.example.ispw.utilities;

import com.example.ispw.exceptions.InvalidFormatException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateTimeslot {

    private ValidateTimeslot() {
    }

    public static boolean isValid(String timeslot) {

        String regex = "^(?:[01]?\\d|2[0-3]):[0-5]?\\d(?::[0-5]?\\d)?$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the str
        // is empty return false
        if (timeslot == null) {
            return false;
        }

        // Pattern class contains matcher()
        // method to find matching between
        // given str using regex.
        Matcher m = p.matcher(timeslot);

        // Return if the str
        // matched the ReGex
        return m.matches();
    }

    public static boolean getMinutes(String timeslot, String epRunningTime) throws InvalidFormatException {
        try {
            LocalTime localTime = LocalTime.parse(timeslot, DateTimeFormatter.ofPattern("HH:mm:ss"));
            LocalTime running = LocalTime.parse(epRunningTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
            int hour = localTime.getHour();
            int minutes = localTime.getMinute();
            int parsedTimeslot = hour * 60 + minutes;
            int hourEp = running.getHour();
            int minutesEp = running.getMinute();
            int parsedRunning = hourEp * 60 + minutesEp;
            return parsedTimeslot < parsedRunning;
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException();
        }
    }
}



