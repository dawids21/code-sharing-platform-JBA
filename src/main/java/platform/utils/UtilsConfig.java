package platform.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import platform.service.model.ProgramMapper;

import java.time.Clock;

@Configuration
public class UtilsConfig {

    @Bean
    public ProgramMapper myMapper(ProgramExpireTimeCalculator calculator) {
        return new ProgramMapper(calculator);
    }

    @Bean
    public ProgramExpireTimeCalculator programExpireTimeCalculator(Clock clock) {
        return new ProgramExpireTimeCalculator(clock);
    }

}
