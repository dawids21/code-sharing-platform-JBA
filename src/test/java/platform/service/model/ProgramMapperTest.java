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
class ProgramMapperTest extends ModelTestBase {

    private final ProgramMapper programMapper = new TestModelConfig().testMyMapper();

    @Test
    void should_map_program_to_program_dto() {
        Program program = testProgram();

        ProgramDto programDto = programMapper.programToProgramDto(program);

        assertThat(programDto).isNotNull();
        assertThat(programDto.getCode()).isEqualTo(program.getCode());
        assertThat(programDto.getDate()).isEqualTo(program.getCreated()
                                                          .format(DateTimeFormatter.ofPattern(
                                                                   "yyyy-MM-dd HH:mm:ss")));
        assertThat(programDto.getTime()).isEqualTo(
                 SECONDS.between(DATE, DATE.plusSeconds(10)));
        assertThat(programDto.getViews()).isEqualTo(
                 program.getViewsAllowed() - program.getCountViews());
        assertThat(programDto.isTimeRestricted()).isTrue();
        assertThat(programDto.isViewsRestricted()).isTrue();
    }

    @Test
    void should_map_program_dto_to_program() {
        ProgramDto programDto = testProgramDto();
        Program program = programMapper.programDtoToProgram(programDto);

        assertThat(program).isNotNull();
        assertThat(program.getCode()).isEqualTo(programDto.getCode());
        assertThat(program.getCreated()).isEqualTo(DATE);
        assertThat(program.getValidUntil()).isEqualTo(DATE.plusSeconds(10));
        assertThat(program.getCountViews()).isEqualTo(0);
        assertThat(program.getViewsAllowed()).isEqualTo(programDto.getViews());
    }

    @Test
    void should_set_time_to_zero_when_valid_until_is_null() {
        Program program = testProgram();
        program.setValidUntil(null);
        ProgramDto programDto = programMapper.programToProgramDto(program);
        assertThat(programDto.getTime()).isEqualTo(0);
    }

    @Test
    void should_set_views_to_zero_when_views_allowed_is_null() {
        Program program = testProgram();
        program.setViewsAllowed(null);
        ProgramDto programDto = programMapper.programToProgramDto(program);
        assertThat(programDto.getViews()).isEqualTo(0);
    }

    @Test
    void should_set_views_allowed_to_null_when_views_is_zero() {
        ProgramDto programDto = testProgramDto();
        programDto.setViews(0);
        Program program = programMapper.programDtoToProgram(programDto);
        assertThat(program.getViewsAllowed()).isNull();
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

    @Test
    void should_set_views_allowed_to_null_when_views_is_negative() {
        ProgramDto programDto = testProgramDto();
        programDto.setViews(-5);
        Program program = programMapper.programDtoToProgram(programDto);
        assertThat(program.getViewsAllowed()).isNull();
    }

    @Test
    void should_mark_dto_as_time_restricted_when_entity_is_time_restricted() {
        Program program = testProgram();
        program.setValidUntil(DATE.plusSeconds(10));
        ProgramDto result = programMapper.programToProgramDto(program);
        assertThat(result.isTimeRestricted()).isTrue();
    }

    @Test
    void should_mark_dto_as_views_restricted_when_entity_is_views_restricted() {
        Program program = testProgram();
        program.setViewsAllowed(program.getCountViews() + 1);
        ProgramDto result = programMapper.programToProgramDto(program);
        assertThat(result.isViewsRestricted()).isTrue();
    }

    @Test
    void should_not_mark_any_restriction_on_dto_when_both_values_are_null_in_entity() {
        Program program = testProgram();
        program.setValidUntil(null);
        program.setViewsAllowed(null);

        ProgramDto result = programMapper.programToProgramDto(program);
        assertThat(result.isTimeRestricted()).isFalse();
        assertThat(result.isViewsRestricted()).isFalse();
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