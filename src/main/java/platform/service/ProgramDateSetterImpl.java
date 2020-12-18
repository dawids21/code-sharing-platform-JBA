package platform.service;

import platform.model.ProgramDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class ProgramDateSetterImpl implements ProgramDateSetter {

    static final DateTimeFormatter DATE_TIME_FORMATTER =
             DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Clock clock;

    ProgramDateSetterImpl(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void setDate(ProgramDto program) {
        LocalDateTime now = LocalDateTime.now(clock);
        program.setDate(now.format(DATE_TIME_FORMATTER));
    }
}