package platform.service;

import platform.model.Program;
import platform.model.ProgramRepository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduledDatabaseRemoveRecords {

    private final ProgramRepository programRepository;
    private final Clock clock;

    public ScheduledDatabaseRemoveRecords(ProgramRepository programRepository,
                                          Clock clock) {
        this.programRepository = programRepository;
        this.clock = clock;
    }

    public void removeRecords() {
        LocalDateTime now = LocalDateTime.now(clock);
        List<Program> programs = programRepository.findAll();
        programs.stream()
                .filter(Program::isRestricted)
                .filter(program -> now.isAfter(program.getValidUntil()) ||
                                   now.isEqual(program.getValidUntil()))
                .forEach(program -> programRepository.deleteById(program.getId()));
    }
}
