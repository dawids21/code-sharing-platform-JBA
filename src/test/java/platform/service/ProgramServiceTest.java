package platform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import platform.model.Program;
import platform.model.ProgramDto;
import platform.model.ProgramRepository;
import platform.utils.MapstructMapper;
import platform.utils.MyMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgramServiceTest {

    private TestProgramDateSetter testProgramDateSetter;
    private ProgramService programService;
    private List<Program> programs;

    @BeforeEach
    void setUp() {
        testProgramDateSetter = new TestProgramDateSetter();
        programs = new ArrayList<>();
        ProgramRepository programRepository = configureDatabaseMock();
        programService = new ProgramService(testProgramDateSetter, programRepository,
                                            new MyMapper(Mappers.getMapper(
                                                     MapstructMapper.class)));
    }

    @Test
    void add_program_should_return_corresponding_id() {
        ProgramDto programDto = new ProgramDto("main()", null);
        long id = programService.addProgram(programDto);
        assertThat(programService.getProgram(id)
                                 .getCode()).isEqualTo("main()");
    }

    @Test
    void use_yyyy_mm_dd_hh_mm_ss_format_for_date() {
        ProgramDto programDto = new ProgramDto("", null);
        long id = programService.addProgram(programDto);
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
        assertThat(programs).hasSize(numOfRequested);
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

    private ProgramRepository configureDatabaseMock() {
        ProgramRepository programRepository = mock(ProgramRepository.class);
        when(programRepository.save(Mockito.any(Program.class))).then(invocation -> {
            Program program = invocation.getArgument(0, Program.class);
            program.setId(programs.size());
            programs.add(invocation.getArgument(0, Program.class));
            return program;
        });
        when(programRepository.findById(Mockito.anyLong())).then(invocation -> {
            long id = invocation.getArgument(0);
            if (id < 0 || id >= programs.size()) {
                return Optional.empty();
            }
            return Optional.of(programs.get((int) id));
        });
        when(programRepository.findAllByOrderByCreatedDesc(
                 Mockito.any(Pageable.class))).then(invocation -> {
            Pageable pageable = invocation.getArgument(0, Pageable.class);
            List<Program> result = new ArrayList<>(programs);
            result.sort(Comparator.comparing(Program::getCreated)
                                  .reversed());
            int start = (int) pageable.getOffset();
            int end = (Math.min(start + pageable.getPageSize(), programs.size()));
            return new PageImpl<>(result.subList(start, end));
        });
        return programRepository;
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