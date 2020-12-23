package platform.service;

import org.junit.jupiter.api.*;
import platform.model.ProgramRepository;
import platform.service.model.Program;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScheduledDatabaseRemoveRecordsTest {

    @Nested
    class TimeRestriction {

        ProgramRepository programRepository;

        @BeforeEach
        void setUp() {
            programRepository = mock(ProgramRepository.class);
            when(programRepository.findAll()).thenReturn(testPrograms());
        }

        Clock testClock(LocalDateTime dateTime) {
            return Clock.fixed(dateTime.atZone(ZoneId.systemDefault())
                                       .toInstant(), ZoneId.systemDefault());

        }

        private List<Program> testPrograms() {
            List<Program> programs = new ArrayList<>();
            programs.add(new Program(1, "", LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                                     LocalDateTime.of(2020, 1, 6, 0, 0, 0), true));
            programs.add(new Program(2, "", LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                                     LocalDateTime.of(2020, 1, 15, 0, 0, 0), false));
            programs.add(new Program(3, "", LocalDateTime.of(2020, 1, 3, 0, 0, 0),
                                     LocalDateTime.of(2020, 1, 30, 0, 0, 0), true));
            return programs;
        }

        @Test
        void should_remove_programs_that_are_no_longer_valid() {
            LocalDateTime now = LocalDateTime.of(2020, 1, 10, 0, 0, 0);
            ScheduledDatabaseRemoveRecords task =
                     new ScheduledDatabaseRemoveRecords(programRepository,
                                                        testClock(now));

            task.removeRecords();

            verify(programRepository, times(1)).deleteById(1L);
        }

        @Test
        void should_remove_programs_at_the_exact_moment_of_invalidation() {
            LocalDateTime now = LocalDateTime.of(2020, 1, 6, 0, 0, 0);
            ScheduledDatabaseRemoveRecords task =
                     new ScheduledDatabaseRemoveRecords(programRepository,
                                                        testClock(now));

            task.removeRecords();

            verify(programRepository, times(1)).deleteById(1L);
        }

        @Test
        void should_remove_only_the_records_that_are_restricted() {
            LocalDateTime now = LocalDateTime.of(2020, 1, 18, 0, 0, 0);
            ScheduledDatabaseRemoveRecords task =
                     new ScheduledDatabaseRemoveRecords(programRepository,
                                                        testClock(now));

            task.removeRecords();

            verify(programRepository, times(0)).deleteById(2L);
        }
    }
}