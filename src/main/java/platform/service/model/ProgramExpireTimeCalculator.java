package platform.service.model;

import platform.service.CurrentDateGetter;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;

public class ProgramExpireTimeCalculator {

    private final CurrentDateGetter currentDateGetter;

    public ProgramExpireTimeCalculator(CurrentDateGetter currentDateGetter) {
        this.currentDateGetter = currentDateGetter;
    }

    public int secondsRemain(LocalDateTime date) {
        if (date == null) {
            return 0;
        }
        LocalDateTime now = currentDateGetter.now();
        return (int) SECONDS.between(now, date);
    }

    public LocalDateTime dateAfterSeconds(int toAdd) {
        return currentDateGetter.now()
                                .plusSeconds(toAdd);
    }
}
