package platform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import platform.service.model.ProgramDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ProgramDateSetterTest {

    ProgramDateSetter programDateSetter;

    private static Stream<Arguments> provideDatesForFormatTests() {
        return Stream.of(Arguments.of(LocalDateTime.of(2020, 12, 12, 12, 12, 12)),
                         Arguments.of(LocalDateTime.of(22, 2, 1, 1, 1, 1)),
                         Arguments.of(LocalDateTime.of(2, 2, 1, 1, 1, 1)),
                         Arguments.of(LocalDateTime.of(220, 2, 1, 1, 1, 1)));
    }

    @BeforeEach
    void setUp() {
        programDateSetter = new TestServiceConfig().testProgramDateSetter();
    }

    @ParameterizedTest
    @MethodSource("provideDatesForFormatTests")
    void use_yyyy_mm_dd_hh_mm_ss_format_for_date(LocalDateTime date) {
        ProgramDateSetter programDateSetter = new ProgramDateSetterImpl(Clock.fixed(
                 date.atZone(ZoneId.systemDefault())
                     .toInstant(), ZoneId.systemDefault()));

        ProgramDto programDto = new ProgramDto("", null, 0);
        programDateSetter.setDate(programDto);

        assertThat(programDto.getDate()).matches(
                 "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
    }

    @Test
    void should_set_now_as_date() {
        ProgramDto programDto = new ProgramDto("", null, 0);
        programDateSetter.setDate(programDto);

        assertThat(programDto.getDate()).isEqualTo("2020-12-22 08:20:21");
    }
}