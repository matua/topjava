package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private DateTimeUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> boolean isBetweenHalfOpen(T lt, T start, T end) {
        if (lt instanceof LocalTime) {
            return ((LocalTime) lt).compareTo((LocalTime) start) >= 0 && ((LocalTime) lt).compareTo((LocalTime) end) < 0;
        } else if (lt instanceof LocalDate) {
            return ((LocalDate) lt).compareTo((LocalDate) start) >= 0 && ((LocalDate) lt).compareTo((LocalDate) end) < 0;
        } else if (lt instanceof LocalDateTime) {
            return ((LocalDateTime) lt).compareTo((LocalDateTime) start) >= 0 && ((LocalDateTime) lt).compareTo((LocalDateTime) end) < 0;
        }
        return true;
    }

    public static String toString(LocalDateTime lt) {
        return lt == null ? "" : lt.format(DATE_TIME_FORMATTER);
    }
}