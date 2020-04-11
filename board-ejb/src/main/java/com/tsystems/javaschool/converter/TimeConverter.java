package com.tsystems.javaschool.converter;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TimeConverter {
    private static final String DATE_FORMATTER = "dd-MM-yyyy HH:mm";

    public String convertDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return localDateTime.format(formatter);
    }

    public LocalDateTime reversedConvertDateTime(String localDateTime) {
        LocalDateTime time = null;

        int day = Integer.parseInt(localDateTime.substring(0, 2));
        int month = Integer.parseInt(localDateTime.substring(3, 5));
        int year = Integer.parseInt(localDateTime.substring(6, 10));
        int hours = Integer.parseInt(localDateTime.substring(11, 13));
        int minutes = Integer.parseInt(localDateTime.substring(14, 16));
        time = LocalDateTime.of(year, month, day, hours, minutes);

        return time;
    }
}

