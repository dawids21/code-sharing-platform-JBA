package platform.integration;

import org.junit.jupiter.api.Disabled;
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
import platform.service.ScheduledProgramRemover;
import platform.service.ServiceConfig;
import platform.service.model.ModelConfig;
import platform.service.model.ProgramExpireTimeCalculator;
import platform.service.model.ProgramMapper;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig({ServiceConfig.class, ModelConfig.class})
@EnableJpaRepositories("platform.service")
@EntityScan("platform.service.model")
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Disabled
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
    void should_autowire_myMapper(@Autowired ProgramMapper programMapper) {
        assertThat(programMapper).isNotNull();
    }

    @Test
    void should_autowire_programExpireTimeCalculator(
             @Autowired ProgramExpireTimeCalculator calculator) {
        assertThat(calculator).isNotNull();
    }

    @Test
    void should_autowire_scheduledDatabaseRemoveRecords(
             @Autowired ScheduledProgramRemover scheduledProgramRemover) {
        assertThat(scheduledProgramRemover).isNotNull();
    }
}
