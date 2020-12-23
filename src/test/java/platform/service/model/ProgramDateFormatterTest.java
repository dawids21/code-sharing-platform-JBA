package platform.service.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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
    @MethodSource({"provideStringDates", "provideDatesForFormatTests"})
    void should_convert_string_dates_to_local_date_time(String stringDate,
                                                        LocalDateTime expected) {
        LocalDateTime date = programDateFormatter.toLocalDateTime(stringDate);
        assertThat(date).isEqualTo(expected);
    }

    private static Stream<Arguments> provideDatesForFormatTests() {
        return Stream.of(Arguments.of(LocalDateTime.of(2020, 12, 12, 12, 12, 12)),
                         Arguments.of(LocalDateTime.of(22, 2, 1, 1, 1, 1)),
                         Arguments.of(LocalDateTime.of(2, 2, 1, 1, 1, 1)),
                         Arguments.of(LocalDateTime.of(220, 2, 1, 1, 1, 1)));
    }

    private static Stream<Arguments> provideStringDates() {
        return Stream.of(Arguments.of("2020-12-12 12:12:12"),
                         Arguments.of("0022-02-01 01:01:01"),
                         Arguments.of("0002-1-1 1:1:1"),
                         Arguments.of("0220-01-01 01:01:01"));
    }
}