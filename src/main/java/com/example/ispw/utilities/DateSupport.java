package com.example.ispw.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateSupport {

    private DateSupport() {}

    public static long daysLeft(LocalDate airingDate) {

        LocalDate today = LocalDate.now();
        long days = DAYS.between(airingDate, today);
        return days;
    }

    public static Date fromStringToDate(String timeslot) {
        Date date = null;

        try {
            date = new SimpleDateFormat("HH:mm:ss").parse(timeslot);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String fromLocalDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, uuuu");
        String output = formatter.format(localDate);
        return output;
    }
}
