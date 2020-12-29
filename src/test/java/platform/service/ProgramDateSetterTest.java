package platform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import platform.service.model.Program;

import static org.assertj.core.api.Assertions.assertThat;

class ProgramDateSetterTest extends ServiceTestBase {

    ProgramDateSetter programDateSetter;

    @BeforeEach
    void setUp() {
        programDateSetter = new TestServiceConfig().testProgramDateSetter();
    }

    @Test
    void should_set_now_as_date() {
        Program program = testValidProgram();
        program.setCreated(null);
        programDateSetter.setCreated(program);

        assertThat(program.getCreated()).isEqualTo(ServiceTestBase.DATE);
    }
}