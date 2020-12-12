package platform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import platform.model.ProgramDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class ProgramServiceTest {

    private final static LocalDateTime DATE_TIME =
             LocalDateTime.of(2020, 12, 12, 10, 32, 21);
    private ProgramService programService;

    @BeforeEach
    void setUp() {
        Clock fixedClock = Clock.fixed(DATE_TIME.atZone(ZoneId.systemDefault())
                                                .toInstant(), ZoneId.systemDefault());
        programService = new ProgramService(fixedClock);
    }

    @Test
    void use_yyyy_mm_dd_hh_mm_ss_format_for_date() {
        ProgramDto programDto = new ProgramDto("", null);
        programService.setProgram(programDto);
        assertThat(programService.getProgram()
                                 .getDate()).isEqualTo("2020-12-12 10:32:21");
    }
}