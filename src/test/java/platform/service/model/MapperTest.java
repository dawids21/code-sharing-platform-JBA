package platform.service.model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MapperTest extends ModelTestBase {

    private final ProgramMapper programMapper = new TestModelConfig().testMyMapper();

    @Test
    void should_map_program_to_program_dto() {
        Program program = new Program();
        program.setCode("main()");
        program.setCreated(DATE);
        program.setValidUntil(DATE.plusSeconds(10));
        program.setViews(10);

        ProgramDto programDto = programMapper.programToProgramDto(program);

        assertThat(programDto).isNotNull();
        assertThat(programDto.getCode()).isEqualTo(program.getCode());
        assertThat(programDto.getDate()).isEqualTo(program.getCreated()
                                                          .format(DateTimeFormatter.ofPattern(
                                                                   "yyyy-MM-dd HH:mm:ss")));
        assertThat(programDto.getTime()).isEqualTo(
                 SECONDS.between(DATE, DATE.plusSeconds(10)));
        assertThat(programDto.getViews()).isEqualTo(10);
    }

    @Test
    void should_map_program_dto_to_program() {
        ProgramDto programDto = testProgramDto();
        Program program = programMapper.programDtoToProgram(programDto);

        assertThat(program).isNotNull();
        assertThat(program.getCode()).isEqualTo(programDto.getCode());
        assertThat(program.getCreated()).isEqualTo(DATE);
        assertThat(program.getValidUntil()).isEqualTo(DATE.plusSeconds(10));
        assertThat(program.getViews()).isEqualTo(10);
    }

    @Test
    void should_set_time_to_zero_when_valid_until_is_null() {
        Program program = new Program();
        program.setValidUntil(null);
        ProgramDto programDto = programMapper.programToProgramDto(program);
        assertThat(programDto.getTime()).isEqualTo(0);
    }

    @Test
    void should_set_valid_until_to_null_when_time_is_zero() {
        ProgramDto programDto = testProgramDto();
        programDto.setTime(0);
        Program program = programMapper.programDtoToProgram(programDto);
        assertThat(program.getValidUntil()).isNull();
    }

    @Test
    void should_set_valid_until_to_null_when_time_is_negative() {
        ProgramDto programDto = testProgramDto();
        programDto.setTime(-3);
        Program program = programMapper.programDtoToProgram(programDto);
        assertThat(program.getValidUntil()).isNull();
    }

    @ParameterizedTest
    @MethodSource("nonRestrictedValuesProvider")
    void should_mark_program_as_not_restricted_when_both_time_or_view_are_negative_or_zero(
             int time, int views) {
        ProgramDto programDto = testProgramDto();
        programDto.setTime(time);
        programDto.setViews(views);

        Program program = programMapper.programDtoToProgram(programDto);

        assertThat(program.isRestricted()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("restrictedValuesProvider")
    void should_mark_program_as_restricted_when_either_time_or_view_is_positive(int time,
                                                                                int views) {
        ProgramDto programDto = testProgramDto();
        programDto.setTime(time);
        programDto.setViews(views);

        Program program = programMapper.programDtoToProgram(programDto);

        assertThat(program.isRestricted()).isTrue();
    }

    private static Stream<Arguments> nonRestrictedValuesProvider() {
        return Stream.of(Arguments.of(0, -1), Arguments.of(0, 0), Arguments.of(-1, -1),
                         Arguments.of(-1, 0));
    }

    private static Stream<Arguments> restrictedValuesProvider() {
        return Stream.of(Arguments.of(0, 1), Arguments.of(1, 1), Arguments.of(1, 0));
    }
}