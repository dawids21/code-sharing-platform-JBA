package platform.utils;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import platform.service.model.MapstructMapper;
import platform.service.model.ProgramMapper;

import java.time.Clock;

@Configuration
public class UtilsConfig {

    @Bean
    public MapstructMapper mapstructMapper() {
        return Mappers.getMapper(MapstructMapper.class);
    }

    @Bean
    public ProgramMapper myMapper(MapstructMapper mapstructMapper,
                                  ProgramExpireTimeCalculator calculator) {
        return new ProgramMapper(mapstructMapper, calculator);
    }

    @Bean
    public ProgramExpireTimeCalculator programExpireTimeCalculator(Clock clock) {
        return new ProgramExpireTimeCalculator(clock);
    }

}
