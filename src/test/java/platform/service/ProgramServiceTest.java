package platform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import platform.model.ProgramDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProgramServiceTest {

    private TestProgramDateSetter testProgramDateSetter;
    private ProgramService programService;

    @BeforeEach
    void setUp() {
        testProgramDateSetter = new TestProgramDateSetter();
        programService = new ProgramService(testProgramDateSetter);
    }

    @Test
    void add_program_should_return_corresponding_id() {
        ProgramDto programDto = new ProgramDto("main()", null);
        int id = programService.addProgram(programDto);
        assertThat(id).isEqualTo(0);
        assertThat(programService.getProgram(id)
                                 .getCode()).isEqualTo("main()");
    }

    @Test
    void use_yyyy_mm_dd_hh_mm_ss_format_for_date() {
        ProgramDto programDto = new ProgramDto("", null);
        int id = programService.addProgram(programDto);
        String expectedDate = testProgramDateSetter.getPreviousDate();
        assertThat(programService.getProgram(id)
                                 .getDate()).isEqualTo(expectedDate);
    }

    @Test
    void should_return_requested_num_of_programs_when_there_is_enough_in_memory() {
        addNPrograms(11);
        List<ProgramDto> programs = programService.getLastPrograms(10);
        assertThat(programs).hasSize(10);
    }

    @Test
    void should_return_max_num_of_programs_when_there_is_less_in_memory() {
        int numOfRequested = 6;
        addNPrograms(numOfRequested);
        List<ProgramDto> programs = programService.getLastPrograms(10);
        assertThat(programs).hasSize(6);
    }

    @Test
    void should_return_empty_list_when_no_programs_in_memory() {
        List<ProgramDto> programs = programService.getLastPrograms(10);
        assertThat(programs).hasSize(0);
    }

    @Test
    void should_sort_programs_by_date() {
        for (int i = 0; i < 4; i++) {
            programService.addProgram(new ProgramDto(String.valueOf(i), null));
        }

        List<ProgramDto> lastPrograms = programService.getLastPrograms(4);

        for (int i = 0; i < 3; i++) {
            assertThat(lastPrograms.get(i)
                                   .getCode()).isGreaterThan(lastPrograms.get(i + 1)
                                                                         .getCode());
        }
    }

    private static class TestProgramDateSetter implements ProgramDateSetter {

        private LocalDateTime previousDate;

        private TestProgramDateSetter() {
            this.previousDate = LocalDateTime.of(2020, 12, 12, 10, 32, 21);
        }

        @Override
        public void setDate(ProgramDto program) {
            previousDate = previousDate.plusMinutes(1);
            program.setDate(previousDate.format(ProgramService.DATE_TIME_FORMATTER));
        }

        public String getPreviousDate() {
            return previousDate.format(ProgramService.DATE_TIME_FORMATTER);
        }
    }

    private void addNPrograms(int n) {
        for (int i = 0; i < n; i++) {
            ProgramDto programDto = new ProgramDto("1 + " + n, null);
            programService.addProgram(programDto);
        }
    }
}