package platform.service.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import platform.service.CurrentDateGetter;

@Configuration
public class ModelConfig {

    @Bean
    public ProgramMapper myMapper(ProgramExpireTimeCalculator calculator) {
        return new ProgramMapper(calculator);
    }

    @Bean
    public ProgramExpireTimeCalculator programExpireTimeCalculator(
             CurrentDateGetter clock) {
        return new ProgramExpireTimeCalculator(clock);
    }
}
