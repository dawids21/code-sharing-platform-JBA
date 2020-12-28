package platform.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

class TestServiceConfig extends ServiceConfig {

    static final LocalDateTime DATE = LocalDateTime.of(2020, 12, 22, 8, 20, 21);
    static final String DATE_STRING = "2020-12-22 08:20:21";
    static final UUID TEST_UUID = UUID.fromString("e6780274-c41c-4ab4-bde6-b32c18b4c489");

    ProgramDateSetter testProgramDateSetter() {
        return programDateSetter(testCurrentDateGetter());
    }

    CurrentDateGetter testCurrentDateGetter() {
        return new CurrentDateGetter(Clock.fixed(DATE.atZone(ZoneId.systemDefault())
                                                     .toInstant(),
                                                 ZoneId.systemDefault()));
    }
}
