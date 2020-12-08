package platform.utils;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import platform.model.Program;
import platform.model.ProgramDto;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MapperTest {

    @Test
    void directly_invoked_mapper_should_map_code_to_code_dto() {
        MapstructMapper mapper = new TestUtilsConfig().testMapstructMapper();
        Program program = new Program();
        program.setCode("main()");

        ProgramDto programDto = mapper.programToProgramDto(program);

        assertThat(programDto).isNotNull();
        assertThat(programDto.getCode()).isEqualTo("main()");
    }

    @Test
    void my_mapper_wrapper_should_work_as_mapstruct_mapper() {
        MyMapper myMapper = new TestUtilsConfig().testMyMapper();
        Program program = new Program();
        program.setCode("main()");

        ProgramDto programDto = myMapper.programToProgramDto(program);

        assertThat(programDto).isNotNull();
        assertThat(programDto.getCode()).isEqualTo("main()");
    }
}