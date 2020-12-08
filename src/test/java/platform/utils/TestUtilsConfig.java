package platform.utils;

import org.mapstruct.factory.Mappers;

class TestUtilsConfig extends UtilsConfig {

    MapstructMapper testMapstructMapper() {
        return Mappers.getMapper(MapstructMapper.class);
    }

    MyMapper testMyMapper() {
        return myMapper(testMapstructMapper());
    }
}
