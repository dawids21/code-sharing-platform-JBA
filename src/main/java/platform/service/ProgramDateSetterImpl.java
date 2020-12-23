package platform.service;

import platform.service.model.ProgramDto;

import java.time.Clock;
import java.time.LocalDateTime;

class ProgramDateSetterImpl implements ProgramDateSetter {

    private final Clock clock;

    ProgramDateSetterImpl(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void setDate(ProgramDto program) {
        LocalDateTime now = LocalDateTime.now(clock);
        program.setDate(now.format(ProgramService.DATE_TIME_FORMATTER));
    }
}