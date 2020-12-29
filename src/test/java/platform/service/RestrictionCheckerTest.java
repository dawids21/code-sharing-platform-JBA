package platform.service;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import platform.service.model.Program;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RestrictionCheckerTest extends ServiceTestBase {

    private final static String baseUUIDString = "e6780274-c41c-4ab4-bde6-b32c18b4c4e";

    @ParameterizedTest
    @MethodSource("provideInvalidRecords")
    void should_return_invalid_for_invalid_records(Program program, LocalDateTime now) {

        RestrictionChecker restrictionChecker =
                 new RestrictionChecker(testCurrentDateGetter(now));

        RestrictionChecker.STATUS result = restrictionChecker.check(program);

        assertThat(result).isEqualTo(RestrictionChecker.STATUS.INVALID);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidRecords")
    void should_return_valid_for_not_restricted_records(Program program,
                                                        LocalDateTime now) {
        program.setRestricted(false);
        RestrictionChecker restrictionChecker =
                 new RestrictionChecker(testCurrentDateGetter(now));

        RestrictionChecker.STATUS result = restrictionChecker.check(program);

        assertThat(result).isEqualTo(RestrictionChecker.STATUS.VALID);
    }

    @ParameterizedTest
    @MethodSource("provideValidRecords")
    void should_return_valid_for_valid_records(Program program, LocalDateTime now) {
        RestrictionChecker restrictionChecker =
                 new RestrictionChecker(testCurrentDateGetter(now));

        RestrictionChecker.STATUS result = restrictionChecker.check(program);

        assertThat(result).isEqualTo(RestrictionChecker.STATUS.VALID);
    }

    @Test
    void should_pass_program_with_null_valid_until() {
        RestrictionChecker restrictionChecker =
                 new TestServiceConfig().testRestrictionChecker();
        Program program = testValidProgram();
        program.setValidUntil(null);

        RestrictionChecker.STATUS result = restrictionChecker.check(program);

        assertThat(result).isEqualTo(RestrictionChecker.STATUS.VALID);
    }

    @Test
    void should_pass_program_with_null_views() {
        RestrictionChecker restrictionChecker =
                 new TestServiceConfig().testRestrictionChecker();
        Program program = testValidProgram();
        program.setViews(null);

        RestrictionChecker.STATUS result = restrictionChecker.check(program);

        assertThat(result).isEqualTo(RestrictionChecker.STATUS.VALID);
    }

    private CurrentDateGetter testCurrentDateGetter(LocalDateTime now) {
        CurrentDateGetter mock = mock(CurrentDateGetter.class);
        when(mock.now()).thenReturn(now);
        return mock;
    }

    //TODO use DATE for test arguments
    private static Stream<Arguments> provideInvalidRecords() {
        return Stream.of(Arguments.of(
                 new Program(UUID.fromString(baseUUIDString + "1"), "",
                             LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                             LocalDateTime.of(2020, 1, 6, 0, 0, 0), 10, true),
                 LocalDateTime.of(2020, 1, 10, 0, 0, 0)), Arguments.of(
                 new Program(UUID.fromString(baseUUIDString + "2"), "",
                             LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                             LocalDateTime.of(2020, 1, 15, 0, 0, 0), -1, true),
                 LocalDateTime.of(2020, 1, 10, 0, 0, 0)), Arguments.of(
                 new Program(UUID.fromString(baseUUIDString + "3"), "",
                             LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                             LocalDateTime.of(2020, 1, 30, 0, 0, 0), 10, true),
                 LocalDateTime.of(2020, 1, 30, 0, 0, 0)), Arguments.of(
                 new Program(UUID.fromString(baseUUIDString + "4"), "",
                             LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                             LocalDateTime.of(2020, 1, 15, 0, 0, 0), -1, true),
                 LocalDateTime.of(2020, 1, 16, 0, 0, 0)));
    }

    private static Stream<Arguments> provideValidRecords() {
        return Stream.of(Arguments.of(
                 new Program(UUID.fromString(baseUUIDString + "1"), "",
                             LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                             LocalDateTime.of(2020, 1, 6, 0, 0, 0), 10, true),
                 LocalDateTime.of(2020, 1, 5, 0, 0, 0)), Arguments.of(
                 new Program(UUID.fromString(baseUUIDString + "2"), "",
                             LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                             LocalDateTime.of(2020, 1, 15, 0, 0, 0), 0, true),
                 LocalDateTime.of(2020, 1, 10, 0, 0, 0)), Arguments.of(
                 new Program(UUID.fromString(baseUUIDString + "3"), "",
                             LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                             LocalDateTime.of(2020, 1, 30, 0, 0, 0), 10, true),
                 LocalDateTime.of(2020, 1, 29, 0, 0, 0)), Arguments.of(
                 new Program(UUID.fromString(baseUUIDString + "4"), "",
                             LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                             LocalDateTime.of(2020, 1, 15, 0, 0, 0), 7, true),
                 LocalDateTime.of(2020, 1, 14, 0, 0, 0)));
    }
}