package platform.utils;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.mapstruct.factory.Mappers;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TestUtilsConfig extends UtilsConfig {

    MapstructMapper testMapstructMapper() {
        return Mappers.getMapper(MapstructMapper.class);
    }

    MyMapper testMyMapper() {
        return myMapper(testMapstructMapper());
    }
}
