package platform.service;

import org.springframework.scheduling.annotation.Scheduled;
import platform.service.model.Program;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduledDatabaseRemoveRecords {

    private final ProgramRepository programRepository;
    private final CurrentDateGetter currentDateGetter;

    public ScheduledDatabaseRemoveRecords(ProgramRepository programRepository,
                                          CurrentDateGetter currentDateGetter) {
        this.programRepository = programRepository;
        this.currentDateGetter = currentDateGetter;
    }

    @Scheduled(fixedRate = 60000)
    public void removeRecords() {
        LocalDateTime now = currentDateGetter.now();
        List<Program> programs = programRepository.findAll();
        programs.stream()
                .filter(Program::isRestricted)
                .filter(program -> now.isAfter(program.getValidUntil()) ||
                                   now.isEqual(program.getValidUntil()))
                .forEach(program -> programRepository.deleteById(program.getId()));
    }
}
