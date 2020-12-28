package platform.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import platform.service.model.Program;

import static org.mockito.Mockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProgramUUIDSetterTest {

    private RandomUUIDGenerator randomUUIDGenerator;
    private ProgramUUIDSetter programUUIDSetter;

    @BeforeEach
    void setUp() {
        randomUUIDGenerator = mock(RandomUUIDGenerator.class);
        when(randomUUIDGenerator.generate()).thenReturn(TestServiceConfig.TEST_UUID);
        programUUIDSetter = new ProgramUUIDSetter(randomUUIDGenerator);
    }

    @Test
    void should_use_generator_for_generating_random_uuid() {
        Program program = new Program();
        programUUIDSetter.setUUID(program);

        verify(randomUUIDGenerator, times(1)).generate();
    }
}