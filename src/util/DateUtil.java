package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(PATTERN));
    }
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}