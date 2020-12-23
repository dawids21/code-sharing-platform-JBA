package platform.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import platform.model.ProgramRepository;
import platform.service.model.MyMapper;

import java.time.Clock;

@Configuration
public class ServiceConfig {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ProgramService programService(ProgramDateSetter programDateSetter,
                                         ProgramRepository programRepository,
                                         MyMapper mapper) {
        return new ProgramService(programDateSetter, programRepository, mapper);
    }

    @Bean
    public ProgramDateSetter programDateSetter(Clock clock) {
        return new ProgramDateSetterImpl(clock);
    }

    @Bean
    public ScheduledDatabaseRemoveRecords scheduledDatabaseRemoveRecords(
             ProgramRepository programRepository, Clock clock) {
        return new ScheduledDatabaseRemoveRecords(programRepository, clock);
    }

}
