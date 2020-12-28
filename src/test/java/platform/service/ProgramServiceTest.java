package platform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import platform.service.model.Program;
import platform.service.model.ProgramDto;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProgramServiceTest {

    private ProgramService programService;

    @BeforeEach
    void setUp() {
        ProgramRepository programRepository = configureDatabaseMock();
        programService = new TestServiceConfig().testProgramService(programRepository);
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