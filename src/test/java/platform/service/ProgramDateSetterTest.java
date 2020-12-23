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
        ProgramDto programDto = new ProgramDto("", null, 0);
        programDateSetter.setDate(programDto);

        assertThat(programDto.getDate()).isEqualTo("2020-12-22 08:20:21");
    }
}