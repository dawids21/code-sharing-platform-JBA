package platform.service;

import platform.service.model.ProgramDto;

import java.time.format.DateTimeFormatter;

public interface ProgramDateSetter {

    DateTimeFormatter DATE_TIME_FORMATTER =
             DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    void setDate(ProgramDto program);
}
