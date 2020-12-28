package platform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import platform.service.model.ProgramDto;

import static org.assertj.core.api.Assertions.assertThat;

class ProgramDateSetterTest {

    ProgramDateSetter programDateSetter;

    @BeforeEach
    void setUp() {
        programDateSetter = new TestServiceConfig().testProgramDateSetter();
    }

    @Test
    void should_set_now_as_date() {
        ProgramDto programDto = new ProgramDto("", null, 0, 10);
        programDateSetter.setDate(programDto);

        assertThat(programDto.getDate()).isEqualTo(ServiceTestBaseClass.DATE_STRING);
    }
}