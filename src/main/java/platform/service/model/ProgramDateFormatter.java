package platform.service.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProgramDateFormatter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
             DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String toString(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    public LocalDateTime toLocalDateTime(String date) {
        return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
    }
}
