package platform;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import platform.service.ProgramDateSetter;
import platform.service.ProgramService;
import platform.utils.MapstructMapper;
import platform.utils.MyMapper;
import platform.utils.UtilsConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(UtilsConfig.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DiTest {

    @Test
    void should_autowire_mapstruct_mapper(@Autowired MapstructMapper mapstructMapper) {
        assertThat(mapstructMapper).isNotNull();
    }

    @Test
    void should_autowire_myMapper(@Autowired MyMapper myMapper) {
        assertThat(myMapper).isNotNull();
    }

    @Test
    void should_autowire_programService(@Autowired ProgramService programService) {
        assertThat(programService).isNotNull();
    }

    @Test
    void should_autowire_programDateSetter(
             @Autowired ProgramDateSetter programDateSetter) {
        assertThat(programDateSetter).isNotNull();
    }
}
