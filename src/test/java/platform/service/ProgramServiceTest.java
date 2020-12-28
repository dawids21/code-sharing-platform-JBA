package platform.service;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import platform.service.model.Program;
import platform.service.model.ProgramDto;
import platform.service.model.ProgramMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProgramServiceTest {

    private ProgramDateSetter programDateSetter;
    private ProgramRepository programRepository;
    private ProgramMapper programMapper;
    private ProgramService programService;

    @BeforeEach
    void setUp() {
        programDateSetter = mock(ProgramDateSetter.class);
        programRepository = configureDatabaseMock();
        programMapper = mock(ProgramMapper.class);
        when(programMapper.programDtoToProgram(Mockito.any(ProgramDto.class))).thenReturn(
                 testProgram());
        when(programMapper.programToProgramDto(Mockito.any(Program.class))).thenReturn(
                 testProgramDto());
        programService =
                 new ProgramService(programDateSetter, programRepository, programMapper);
    }

    @Nested
    class addProgram {

        @Test
        void should_map_dto_to_entity_using_mapper() {
            ProgramDto programDto = testProgramDto();
            programService.addProgram(programDto);

            verify(programMapper, times(1)).programDtoToProgram(programDto);
        }

        @Test
        void should_set_date_of_program() {
            ProgramDto programDto = testProgramDto();
            programService.addProgram(programDto);

            verify(programDateSetter, times(1)).setDate(programDto);
        }

        @Test
        void should_save_program_in_repository() {
            programService.addProgram(testProgramDto());

            verify(programRepository, times(1)).save(Mockito.any(Program.class));
        }

        @Test
        void should_return_corresponding_uuid() {
            UUID id = programService.addProgram(testProgramDto());

            assertThat(id.toString()).isEqualTo(TestServiceConfig.TEST_UUID.toString());
        }
    }

    @Nested
    class getProgram {

        @Test
        void should_map_entity_to_dto_using_mapper() {
            programService.getProgram(TestServiceConfig.TEST_UUID);

            verify(programMapper, times(1)).programToProgramDto(
                     Mockito.any(Program.class));
        }

        @Test
        void should_search_in_database_for_program() {
            UUID id = TestServiceConfig.TEST_UUID;
            programService.getProgram(id);

            verify(programRepository, times(1)).findById(id);
        }

        @Test
        void should_throw_exception_with_status_not_found_when_id_does_not_exist() {
            assertThatThrownBy(() -> programService.getProgram(UUID.fromString(
                     "e6780274-c41c-4ab4-bde6-124141241241"))).isInstanceOf(
                     ResponseStatusException.class)
                                                              .hasFieldOrPropertyWithValue(
                                                                       "status",
                                                                       HttpStatus.NOT_FOUND);

        }
    }

    @Nested
    class getLastPrograms {

        @Test
        void should_return_num_of_programs_requested_by_user() {
            List<ProgramDto> lastPrograms = programService.getLastPrograms(1);
            assertThat(lastPrograms).hasSize(1);
        }

        @Test
        void should_return_all_programs_when_user_requested_more_than_in_db() {
            List<ProgramDto> lastPrograms = programService.getLastPrograms(3);
            assertThat(lastPrograms).hasSize(1);
        }

        @Test
        void should_map_results_using_mapper() {
            programService.getLastPrograms(1);

            verify(programMapper, times(1)).programToProgramDto(
                     Mockito.any(Program.class));
        }
    }

    private ProgramRepository configureDatabaseMock() {
        ProgramRepository mock = mock(ProgramRepository.class);
        when(mock.save(Mockito.any(Program.class))).then(i -> {
            Program program = i.getArgument(0, Program.class);
            program.setId(TestServiceConfig.TEST_UUID);
            return program;
        });
        when(mock.findById(Mockito.any(UUID.class))).then(i -> {
            UUID index = i.getArgument(0, UUID.class);
            if (index.equals(TestServiceConfig.TEST_UUID)) {
                return Optional.of(testProgram());
            } else {
                return Optional.empty();
            }
        });
        Page<Program> programPage = new PageImpl<>(List.of(testProgram()));
        when(mock.findNotRestricted(Mockito.any(Pageable.class))).thenReturn(programPage);
        return mock;
    }

    Program testProgram() {
        return new Program(TestServiceConfig.TEST_UUID, "main()", TestServiceConfig.DATE,
                           TestServiceConfig.DATE.plusSeconds(10), 10, true);
    }

    ProgramDto testProgramDto() {
        return new ProgramDto("main()", TestServiceConfig.DATE_STRING, 10, 10);
    }
}