package platform.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import platform.service.model.ProgramMapper;

import java.time.Clock;

@Configuration
public class ServiceConfig {

    @Bean
    public ProgramService programService(ProgramDateSetter programDateSetter,
                                         ProgramRepository programRepository,
                                         ProgramMapper mapper) {
        return new ProgramService(programDateSetter, programRepository, mapper,
                                  new ProgramViewsReducer());
    }

    @Bean
    public CurrentDateGetter currentDateGetter() {
        return new CurrentDateGetter(Clock.systemDefaultZone());
    }

    @Bean
    public ProgramDateSetter programDateSetter(CurrentDateGetter currentDateGetter) {
        return new ProgramDateSetterImpl(currentDateGetter);
    }

    @Bean
    public ScheduledProgramRemover scheduledDatabaseRemoveRecords(
             ProgramRepository programRepository, CurrentDateGetter currentDateGetter) {
        return new ScheduledProgramRemover(programRepository,
                                           new RestrictionChecker(currentDateGetter));
    }

}
