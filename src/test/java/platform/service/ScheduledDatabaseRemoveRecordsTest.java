package platform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import platform.service.model.Program;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScheduledDatabaseRemoveRecordsTest extends ServiceTestBase {

    private final static UUID validUUID =
             UUID.fromString("e6780274-c41c-4ab4-bde6-b32c18b4c4e1");
    private final static UUID invalidUUID =
             UUID.fromString("e6780274-c41c-4ab4-bde6-b32c18b4c4e2");

    private ProgramRepository programRepository;
    private ScheduledDatabaseRemoveRecords task;

    @BeforeEach
    void setUp() {
        programRepository = mock(ProgramRepository.class);
        when(programRepository.findAll()).thenReturn(testPrograms());
        task = new ScheduledDatabaseRemoveRecords(programRepository,
                                                  new TestServiceConfig().testRestrictionChecker());
    }

    @Test
    void should_remove_programs_that_are_invalid() {
        task.removeRecords();

        verify(programRepository, times(0)).deleteById(validUUID);
        verify(programRepository, times(1)).deleteById(invalidUUID);
    }

    private List<Program> testPrograms() {
        Program validProgram = testProgram();
        validProgram.setId(validUUID);
        Program invalidProgram = testProgram();
        invalidProgram.setId(invalidUUID);
        invalidProgram.setCreated(DATE.minusSeconds(10L));
        invalidProgram.setValidUntil(DATE.minusSeconds(1L));

        List<Program> programs = new ArrayList<>();
        programs.add(validProgram);
        programs.add(invalidProgram);
        return programs;
    }
}