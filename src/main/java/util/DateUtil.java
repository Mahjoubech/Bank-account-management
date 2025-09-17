package main.java.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtil {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String formatDate(LocalDateTime date) {
        return date.format(FORMATTER);
    }

    public static LocalDateTime parseDate(String dateStr) {
        return LocalDateTime.parse(dateStr, FORMATTER);
    }
}