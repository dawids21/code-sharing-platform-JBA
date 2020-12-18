package platform.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import platform.model.ProgramRepository;

import java.time.Clock;

@Configuration
public class ServiceConfig {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ProgramService programService(ProgramDateSetter programDateSetter,
                                         ProgramRepository programRepository) {
        return new ProgramService(programDateSetter, programRepository);
    }

    @Bean
    public ProgramDateSetter programDateSetter(Clock clock) {
        return new ProgramDateSetterImpl(clock);
    }

}
