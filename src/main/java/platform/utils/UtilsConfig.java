package platform.utils;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

    @Bean
    public HtmlCreator htmlCreator() {
        return new HtmlCreator();
    }

    @Bean
    public MapstructMapper mapstructMapper() {
        return Mappers.getMapper(MapstructMapper.class);
    }

    @Bean
    public MyMapper myMapper(MapstructMapper mapstructMapper) {
        return new MyMapper(mapstructMapper);
    }

}
