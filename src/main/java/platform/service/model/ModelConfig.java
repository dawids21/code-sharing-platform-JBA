package platform.service.model;

import org.springframework.context.annotation.Bean;

import java.time.Clock;

public class ModelConfig {

    @Bean
    public ProgramMapper myMapper(ProgramExpireTimeCalculator calculator) {
        return new ProgramMapper(calculator);
    }

    @Bean
    public ProgramExpireTimeCalculator programExpireTimeCalculator(Clock clock) {
        return new ProgramExpireTimeCalculator(clock);
    }
}
