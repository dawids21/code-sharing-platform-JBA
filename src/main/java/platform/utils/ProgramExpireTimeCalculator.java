package platform.utils;

import java.time.Clock;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;

public class ProgramExpireTimeCalculator {

    private final Clock clock;

    public ProgramExpireTimeCalculator(Clock clock) {
        this.clock = clock;
    }

    public int secondsRemain(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now(clock);
        return (int) SECONDS.between(now, date);
    }

    public LocalDateTime dateAfterSeconds(int toAdd) {
        return LocalDateTime.now(clock)
                            .plusSeconds(toAdd);
    }
}
