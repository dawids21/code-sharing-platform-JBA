package platform.service;

import org.junit.jupiter.api.*;
import platform.service.model.Program;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScheduledDatabaseRemoveRecordsTest extends ServiceTestBase {

    private final String baseUUIDString = "e6780274-c41c-4ab4-bde6-b32c18b4c4e";

    @Nested
    class TimeRestriction {

        ProgramRepository programRepository;

        @BeforeEach
        void setUp() {
            programRepository = mock(ProgramRepository.class);
            when(programRepository.findAll()).thenReturn(testPrograms());
        }

        @Test
        void should_remove_programs_that_are_no_longer_valid() {
            LocalDateTime now = LocalDateTime.of(2020, 1, 10, 0, 0, 0);
            ScheduledDatabaseRemoveRecords task =
                     new ScheduledDatabaseRemoveRecords(programRepository,
                                                        testCurrentDateGetter(now));

            task.removeRecords();

            verify(programRepository, times(1)).deleteById(
                     UUID.fromString(baseUUIDString + "1"));
        }

        @Test
        void should_remove_programs_at_the_exact_moment_of_invalidation() {
            LocalDateTime now = LocalDateTime.of(2020, 1, 6, 0, 0, 0);
            ScheduledDatabaseRemoveRecords task =
                     new ScheduledDatabaseRemoveRecords(programRepository,
                                                        testCurrentDateGetter(now));

            task.removeRecords();

            verify(programRepository, times(1)).deleteById(
                     UUID.fromString(baseUUIDString + "1"));
        }

        @Test
        void should_remove_only_the_records_that_are_restricted() {
            LocalDateTime now = LocalDateTime.of(2020, 1, 18, 0, 0, 0);
            ScheduledDatabaseRemoveRecords task =
                     new ScheduledDatabaseRemoveRecords(programRepository,
                                                        testCurrentDateGetter(now));

            task.removeRecords();

            verify(programRepository, times(0)).deleteById(
                     UUID.fromString(baseUUIDString + "2"));
        }

        private CurrentDateGetter testCurrentDateGetter(LocalDateTime now) {
            CurrentDateGetter mock = mock(CurrentDateGetter.class);
            when(mock.now()).thenReturn(now);
            return mock;
        }

        private List<Program> testPrograms() {
            List<Program> programs = new ArrayList<>();
            programs.add(new Program(UUID.fromString(baseUUIDString + "1"), "",
                                     LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                                     LocalDateTime.of(2020, 1, 6, 0, 0, 0), 10, true));
            programs.add(new Program(UUID.fromString(baseUUIDString + "2"), "",
                                     LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                                     LocalDateTime.of(2020, 1, 15, 0, 0, 0), 0, false));
            programs.add(new Program(UUID.fromString(baseUUIDString + "3"), "",
                                     LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                                     LocalDateTime.of(2020, 1, 30, 0, 0, 0), 10, true));
            return programs;
        }
    }
}