package platform.service;

import java.time.Clock;
import java.time.ZoneId;

class TestServiceConfig extends ServiceConfig {

    ProgramDateSetter testProgramDateSetter() {
        return programDateSetter(testCurrentDateGetter());
    }

    CurrentDateGetter testCurrentDateGetter() {
        return new CurrentDateGetter(testClock());
    }

    private Clock testClock() {
        return Clock.fixed(ServiceTestBase.DATE.atZone(ZoneId.systemDefault())
                                               .toInstant(), ZoneId.systemDefault());
    }

    RestrictionChecker testRestrictionChecker() {
        return new RestrictionChecker(testCurrentDateGetter());
    }
}
