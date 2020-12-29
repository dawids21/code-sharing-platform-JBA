package platform.service;

import platform.service.model.Program;

import java.time.LocalDateTime;

public class RestrictionChecker {

    private final CurrentDateGetter currentDateGetter;

    public RestrictionChecker(CurrentDateGetter currentDateGetter) {
        this.currentDateGetter = currentDateGetter;
    }

    public STATUS check(Program program) {
        if (!program.isRestricted()) {
            return STATUS.VALID;
        }

        if (STATUS.INVALID.equals(checkTimeRestriction(program))) {
            return STATUS.INVALID;
        }
        if (STATUS.INVALID.equals(checkViewsRestriction(program))) {
            return STATUS.INVALID;
        }
        return STATUS.VALID;
    }

    private STATUS checkViewsRestriction(Program program) {
        if (program.getViews() != null && program.getViews() <= 0) {
            return STATUS.INVALID;
        }
        return STATUS.VALID;
    }

    private STATUS checkTimeRestriction(Program program) {
        LocalDateTime now = currentDateGetter.now();
        LocalDateTime validUntil = program.getValidUntil();
        if (validUntil != null && (now.isAfter(validUntil) || now.isEqual(validUntil))) {
            return STATUS.INVALID;
        }
        return STATUS.VALID;
    }

    public enum STATUS {
        VALID, INVALID
    }
}
