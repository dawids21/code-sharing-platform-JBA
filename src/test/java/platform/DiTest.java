package platform;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import platform.service.ProgramDateSetter;
import platform.service.ProgramService;
import platform.service.ScheduledDatabaseRemoveRecords;
import platform.service.ServiceConfig;
import platform.service.model.MapstructMapper;
import platform.service.model.MyMapper;
import platform.utils.ProgramExpireTimeCalculator;
import platform.utils.UtilsConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig({ServiceConfig.class, UtilsConfig.class})
@EnableJpaRepositories("platform.model")
@EntityScan("platform.model")
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DiTest {

    @Test
    void should_autowire_programService(@Autowired ProgramService programService) {
        assertThat(programService).isNotNull();
    }

    @Test
    void should_autowire_programDateSetter(
             @Autowired ProgramDateSetter programDateSetter) {
        assertThat(programDateSetter).isNotNull();
    }

    @Test
    void should_autowire_mapstruct_mapper(@Autowired MapstructMapper mapstructMapper) {
        assertThat(mapstructMapper).isNotNull();
    }

    @Test
    void should_autowire_myMapper(@Autowired MyMapper myMapper) {
        assertThat(myMapper).isNotNull();
    }

    @Test
    void should_autowire_programExpireTimeCalculator(
             @Autowired ProgramExpireTimeCalculator calculator) {
        assertThat(calculator).isNotNull();
    }

    @Test
    void should_autowire_scheduledDatabaseRemoveRecords(
             @Autowired ScheduledDatabaseRemoveRecords scheduledDatabaseRemoveRecords) {
        assertThat(scheduledDatabaseRemoveRecords).isNotNull();
    }
}
