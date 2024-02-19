package com.example.ispw.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateSupport {

    private DateSupport() {}

    public static long daysLeft(LocalDate airingDate) {

        LocalDate today = LocalDate.now();
        return DAYS.between(airingDate, today);
    }



    public static String fromLocalDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, uuuu");
        return formatter.format(localDate);
    }
}
