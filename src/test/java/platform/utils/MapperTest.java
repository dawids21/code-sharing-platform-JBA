package platform.utils;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import platform.model.Program;
import platform.model.ProgramDto;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MapperTest {

    @Test
    void should_map_program_to_program_dto() {
        MyMapper myMapper = new TestUtilsConfig().testMyMapper();
        Program program = new Program();
        program.setCode("main()");

        ProgramDto programDto = myMapper.programToProgramDto(program);

        assertThat(programDto).isNotNull();
        assertThat(programDto.getCode()).isEqualTo("main()");
        assertThat(programDto.getDate()).isEqualTo(LocalDateTime.now());
    }
}