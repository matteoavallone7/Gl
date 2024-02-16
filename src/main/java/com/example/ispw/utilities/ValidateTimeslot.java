package com.example.ispw.utilities;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateTimeslot {

    private ValidateTimeslot() {}

    public static boolean isValid(String timeslot) {

        String regex = "^(?:[01]?[0-9]|2[0-3]):[0-5]?[0-9](?::[0-5]?[0-9])?$";

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

    public static boolean getMinutes(String timeslot, String epRunningTime) {
        LocalTime localTime = LocalTime.parse(timeslot, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime running = LocalTime.parse(epRunningTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        int hour = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
        int minutes = localTime.get(ChronoField.MINUTE_OF_HOUR);
        int parsedTimeslot = hour*60+minutes;
        int hourEp = running.get(ChronoField.CLOCK_HOUR_OF_DAY);
        int minutesEp = running.get(ChronoField.MINUTE_OF_HOUR);
        int parsedRunning = hourEp*60+minutesEp;
        return parsedTimeslot < parsedRunning;
    }

}

