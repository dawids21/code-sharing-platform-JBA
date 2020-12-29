package platform.service;

import platform.service.model.Program;

import java.time.LocalDateTime;

public class RestrictionChecker {

    private final CurrentDateGetter currentDateGetter;

    public RestrictionChecker(CurrentDateGetter currentDateGetter) {
        this.currentDateGetter = currentDateGetter;
    }

    public boolean check(Program program) {
        if (!program.isRestricted()) {
            return true;
        }
        return checkTimeRestriction(program) && checkViewRestriction(program);
    }

    private boolean checkViewRestriction(Program program) {
        return program.getViews() > 0;
    }

    private boolean checkTimeRestriction(Program program) {
        LocalDateTime now = currentDateGetter.now();
        return now.isBefore(program.getValidUntil());
    }
}
