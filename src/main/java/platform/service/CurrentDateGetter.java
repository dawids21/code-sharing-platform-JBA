package platform.service;

import java.time.Clock;
import java.time.LocalDateTime;

public class CurrentDateGetter {

    private final Clock clock;

    public CurrentDateGetter(Clock clock) {
        this.clock = clock;
    }

    public LocalDateTime now() {
        return LocalDateTime.now(clock);
    }
}
