package platform.service.model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MapperTest {

    private final ProgramMapper programMapper = new TestModelConfig().testMyMapper();

    @Test
    void should_map_program_to_program_dto() {
        Program program = new Program();
        program.setCode("main()");
        program.setCreated(TestModelConfig.DATE);
        program.setValidUntil(TestModelConfig.DATE);

        ProgramDto programDto = programMapper.programToProgramDto(program);

        assertThat(programDto).isNotNull();
        assertThat(programDto.getCode()).isEqualTo(program.getCode());
        assertThat(programDto.getDate()).isEqualTo(program.getCreated()
                                                          .format(DateTimeFormatter.ofPattern(
                                                                   "yyyy-MM-dd HH:mm:ss")));
        assertThat(programDto.getTime()).isEqualTo(
                 SECONDS.between(TestModelConfig.DATE, TestModelConfig.DATE));
    }

    @Test
    void should_map_program_dto_to_program() {
        ProgramDto programDto = testProgramDto();
        Program program = programMapper.programDtoToProgram(programDto);

        assertThat(program).isNotNull();
        assertThat(program.getCode()).isEqualTo(programDto.getCode());
        assertThat(program.getCreated()).isEqualTo(TestModelConfig.DATE);
        assertThat(program.getValidUntil()).isNull();
    }

    @Test
    void should_set_time_to_zero_when_valid_until_is_null() {
        Program program = new Program();
        program.setValidUntil(null);
        ProgramDto programDto = programMapper.programToProgramDto(program);
        assertThat(programDto.getTime()).isEqualTo(0);
    }

    @Test
    void should_mark_the_program_as_not_restricted_when_dto_has_zero_in_time_field() {
        ProgramDto programDto = testProgramDto();
        programDto.setTime(0);
        Program program = programMapper.programDtoToProgram(programDto);
        assertThat(program.isRestricted()).isFalse();
    }

    @Test
    void should_mark_the_program_as_not_restricted_when_dto_has_negative_number_in_time_field() {
        ProgramDto programDto = testProgramDto();
        programDto.setTime(-4);
        Program program = programMapper.programDtoToProgram(programDto);
        assertThat(program.isRestricted()).isFalse();
    }

    @Test
    void should_mark_the_program_as_restricted_when_dto_has_positive_number_in_time_field() {
        ProgramDto programDto = testProgramDto();
        programDto.setTime(23);
        Program program = programMapper.programDtoToProgram(programDto);
        assertThat(program.isRestricted()).isTrue();
    }

    private ProgramDto testProgramDto() {
        return new ProgramDto("main()", TestModelConfig.DATE.format(
                 DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 0);
    }

}