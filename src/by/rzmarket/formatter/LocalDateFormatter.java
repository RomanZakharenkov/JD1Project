package by.rzmarket.formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateFormatter {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate format(String value) {
        return LocalDate.parse(value, FORMATTER);
    }
}
