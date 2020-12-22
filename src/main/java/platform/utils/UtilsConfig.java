package platform.utils;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class UtilsConfig {

    @Bean
    public MapstructMapper mapstructMapper() {
        return Mappers.getMapper(MapstructMapper.class);
    }

    @Bean
    public MyMapper myMapper(MapstructMapper mapstructMapper,
                             ProgramExpireTimeCalculator calculator) {
        return new MyMapper(mapstructMapper, calculator);
    }

    @Bean
    public ProgramExpireTimeCalculator programExpireTimeCalculator(Clock clock) {
        return new ProgramExpireTimeCalculator(clock);
    }

}
