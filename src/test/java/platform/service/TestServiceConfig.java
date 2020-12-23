package platform.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

class TestServiceConfig extends ServiceConfig {

    static final LocalDateTime DATE = LocalDateTime.of(2020, 12, 22, 8, 20, 21);
    static final String DATE_STRING = "2020-12-22 08:20:21";

    ProgramDateSetter testProgramDateSetter() {
        return programDateSetter(testCurrentDateGetter());
    }

    public CurrentDateGetter testCurrentDateGetter() {
        return new CurrentDateGetter(Clock.fixed(DATE.atZone(ZoneId.systemDefault())
                                                     .toInstant(),
                                                 ZoneId.systemDefault()));
    }

}
