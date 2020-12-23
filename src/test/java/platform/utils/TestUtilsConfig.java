package platform.utils;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.mapstruct.factory.Mappers;
import platform.service.model.MapstructMapper;
import platform.service.model.ProgramMapper;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TestUtilsConfig extends UtilsConfig {

    public static final LocalDateTime DATE = LocalDateTime.of(2020, 12, 22, 8, 20, 21);

    MapstructMapper testMapstructMapper() {
        return Mappers.getMapper(MapstructMapper.class);
    }

    ProgramMapper testMyMapper() {
        return myMapper(testMapstructMapper(), programExpireTimeCalculator(testClock()));
    }

    public Clock testClock() {
        return Clock.fixed(DATE.atZone(ZoneId.systemDefault())
                               .toInstant(), ZoneId.systemDefault());
    }

}
