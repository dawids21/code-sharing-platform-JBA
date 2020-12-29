package platform.service;

import org.springframework.scheduling.annotation.Scheduled;
import platform.service.model.Program;

import java.util.List;

public class ScheduledDatabaseRemoveRecords {

    private final ProgramRepository programRepository;
    private final RestrictionChecker restrictionChecker;

    public ScheduledDatabaseRemoveRecords(ProgramRepository programRepository,
                                          RestrictionChecker restrictionChecker) {
        this.programRepository = programRepository;
        this.restrictionChecker = restrictionChecker;
    }

    @Scheduled(fixedRate = 60000)
    public void removeRecords() {
        List<Program> programs = programRepository.findAll();
        programs.stream()
                .filter(program -> restrictionChecker.check(program) ==
                                   RestrictionChecker.STATUS.INVALID)
                .forEach(program -> programRepository.deleteById(program.getId()));
    }
}
