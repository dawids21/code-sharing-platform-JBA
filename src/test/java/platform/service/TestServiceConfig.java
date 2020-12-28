package platform.service;

import java.time.Clock;
import java.time.ZoneId;

class TestServiceConfig extends ServiceConfig {

    ProgramDateSetter testProgramDateSetter() {
        return programDateSetter(testCurrentDateGetter());
    }

    CurrentDateGetter testCurrentDateGetter() {
        return new CurrentDateGetter(Clock.fixed(
                 ServiceTestBaseClass.DATE.atZone(ZoneId.systemDefault())
                                          .toInstant(), ZoneId.systemDefault()));
    }
}
