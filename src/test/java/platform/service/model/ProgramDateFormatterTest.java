package platform.service.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProgramDateFormatterTest {

    private ProgramDateFormatter programDateFormatter;

    @BeforeEach
    void setUp() {
        programDateFormatter = new ProgramDateFormatter();
    }

    @ParameterizedTest
    @MethodSource("provideDatesForFormatTests")
    void use_yyyy_mm_dd_hh_mm_ss_format_for_date(LocalDateTime date) {
        String stringDate = programDateFormatter.toString(date);
        assertThat(stringDate).matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
    }

    @ParameterizedTest
    @MethodSource("provideStringDates")
    void should_convert_string_dates_to_local_date_time(String stringDate,
                                                        LocalDateTime expected) {
        LocalDateTime date = programDateFormatter.toLocalDateTime(stringDate);
        assertThat(date).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideNonParsableDates")
    void should_throw_exception_when_non_parsable_date(String nonParsableDate) {
        assertThatThrownBy(() -> programDateFormatter.toLocalDateTime(
                 nonParsableDate)).isInstanceOf(DateTimeParseException.class);
    }

    private static Stream<Arguments> provideDatesForFormatTests() {
        return Stream.of(Arguments.of(LocalDateTime.of(2020, 12, 12, 12, 12, 12)),
                         Arguments.of(LocalDateTime.of(22, 2, 1, 1, 1, 1)),
                         Arguments.of(LocalDateTime.of(2, 2, 1, 1, 1, 1)),
                         Arguments.of(LocalDateTime.of(220, 2, 1, 1, 1, 1)));
    }

    private static Stream<Arguments> provideStringDates() {
        return Stream.of(Arguments.of("2020-12-12 12:12:12",
                                      LocalDateTime.of(2020, 12, 12, 12, 12, 12)),
                         Arguments.of("0022-01-01 01:01:01",
                                      LocalDateTime.of(22, 1, 1, 1, 1, 1)),
                         Arguments.of("0002-01-01 01:01:01",
                                      LocalDateTime.of(2, 1, 1, 1, 1, 1)),
                         Arguments.of("0220-01-01 01:01:01",
                                      LocalDateTime.of(220, 1, 1, 1, 1, 1)));
    }

    private static Stream<Arguments> provideNonParsableDates() {
        return Stream.of(Arguments.of("2020-2-12 12:12:12"),
                         Arguments.of("22-01-01 01:01:01"),
                         Arguments.of("0002-01-01 01:01:1"),
                         Arguments.of("0220-01-01 1:01:01"));
    }
}