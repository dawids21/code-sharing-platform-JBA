package platform.service.model;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestModelConfig extends ModelConfig {

    static final LocalDateTime DATE = LocalDateTime.of(2020, 12, 22, 8, 20, 21);

    ProgramMapper testMyMapper() {
        return myMapper(programExpireTimeCalculator(testClock()));
    }

    public Clock testClock() {
        return Clock.fixed(DATE.atZone(ZoneId.systemDefault())
                               .toInstant(), ZoneId.systemDefault());
    }

}
