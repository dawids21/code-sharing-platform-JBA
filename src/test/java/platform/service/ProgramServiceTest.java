package platform.service;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import platform.service.model.Program;
import platform.service.model.ProgramDto;
import platform.service.model.ProgramMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProgramServiceTest {

    private ProgramService programService;

    @BeforeEach
    void setUp() {
        ProgramRepository programRepository = configureDatabaseMock();

        programService = new TestServiceConfig().testProgramService(programRepository);
    }

    @Nested
    class addProgram {

        @Test
        void should_map_dto_to_entity_using_mapper() {
            ProgramMapper mapperMock = mock(ProgramMapper.class);
            when(mapperMock.programDtoToProgram(
                     Mockito.any(ProgramDto.class))).thenReturn(testProgram());
            ProgramService programService =
                     new ProgramService(new TestServiceConfig().testProgramDateSetter(),
                                        configureDatabaseMock(), mapperMock);
            ProgramDto programDto = testProgramDto();
            programService.addProgram(programDto);

            verify(mapperMock, times(1)).programDtoToProgram(programDto);
        }

        @Test
        void should_set_date_of_program() {
            //TODO implement should_set_date_of_program
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void should_save_program_in_repository() {
            //TODO implement should_save_program_in_repository
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void should_return_corresponding_id() {
            //TODO implement should_return_corresponding_id
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    @Nested
    class getProgram {

        @Test
        void should_map_entity_to_dto_using_mapper() {
            //TODO implement should_map_entity_to_dto_using_mapper
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void should_search_in_database_for_program() {
            //TODO implement should_search_in_database_for_program
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void should_throw_exception_when_id_does_not_exist() {
            //TODO implement should_throw_exception_when_id_does_not_exist
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    @Nested
    class getLastPrograms {

        @Test
        void should_return_num_of_programs_requested_by_user() {
            //TODO implement should_return_num_of_programs_requested_by_user
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void should_return_all_programs_when_user_requested_more_than_in_db() {
            //TODO implement should_return_all_programs_when_user_requested_more_than_in_db
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void should_map_results_using_mapper() {
            //TODO implement should_map_results_using_mapper
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    private ProgramRepository configureDatabaseMock() {
        ProgramRepository mock = mock(ProgramRepository.class);
        when(mock.save(Mockito.any(Program.class))).then(i -> {
            Program program = i.getArgument(0, Program.class);
            program.setId(1);
            return program;
        });
        when(mock.findById(Mockito.anyLong())).then(i -> {
            long index = i.getArgument(0, Long.class);
            if (index == 1) {
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
        return new Program(1, "main()", TestServiceConfig.DATE,
                           TestServiceConfig.DATE.plusSeconds(10), true);
    }

    ProgramDto testProgramDto() {
        return new ProgramDto("main()", TestServiceConfig.DATE_STRING, 10);
    }
}