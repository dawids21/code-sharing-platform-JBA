package platform.service;

import platform.service.model.ProgramExpireTimeCalculator;
import platform.service.model.ProgramMapper;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

class TestServiceConfig extends ServiceConfig {

    static final LocalDateTime DATE = LocalDateTime.of(2020, 12, 22, 8, 20, 21);
    static final String DATE_STRING = "2020-12-22 08:20:21";

    ProgramDateSetter testProgramDateSetter() {
        return programDateSetter(testCurrentDateGetter());
    }

    CurrentDateGetter testCurrentDateGetter() {
        return new CurrentDateGetter(Clock.fixed(DATE.atZone(ZoneId.systemDefault())
                                                     .toInstant(),
                                                 ZoneId.systemDefault()));
    }

    ProgramService testProgramService(ProgramRepository programRepository) {
        return programService(testProgramDateSetter(), programRepository,
                              testProgramMapper());
    }

    private ProgramMapper testProgramMapper() {
        return new ProgramMapper(
                 new ProgramExpireTimeCalculator(testCurrentDateGetter()));
    }
}
