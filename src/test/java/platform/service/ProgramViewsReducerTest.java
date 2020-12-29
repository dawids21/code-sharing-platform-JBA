package platform.service;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import platform.service.model.Program;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProgramViewsReducerTest extends ServiceTestBase {

    private final ProgramViewsReducer programViewsReducer = new ProgramViewsReducer();

    @Test
    void should_reduce_views_by_one_if_restricted() {
        Program program = testValidProgram();
        program.setViews(4);
        program.setRestricted(true);

        Program result = programViewsReducer.reduce(program);

        assertThat(result.getViews()).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("provideViews")
    void should_reduce_views_only_when_they_are_greater_or_equal_to_zero(int views,
                                                                         int expected) {
        Program program = testValidProgram();
        program.setViews(views);
        program.setRestricted(true);

        Program result = programViewsReducer.reduce(program);

        assertThat(result.getViews()).isEqualTo(expected);
    }

    @Test
    void should_not_reduce_views_when_program_is_not_restricted() {
        Program program = testValidProgram();
        program.setViews(4);
        program.setRestricted(false);

        Program result = programViewsReducer.reduce(program);
        assertThat(result.getViews()).isEqualTo(4);
    }

    @Test
    void should_omit_reducing_when_views_is_null() {
        Program program = testValidProgram();
        program.setViews(null);
        program.setRestricted(true);

        Program result = programViewsReducer.reduce(program);
        assertThat(result.getViews()).isNull();
    }

    private static Stream<Arguments> provideViews() {
        return Stream.of(Arguments.of(-1, -1), Arguments.of(0, -1), Arguments.of(1, 0));
    }
}