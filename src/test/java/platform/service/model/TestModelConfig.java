package platform.service.model;

import platform.service.CurrentDateGetter;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestModelConfig extends ModelConfig {

    static final LocalDateTime DATE = LocalDateTime.of(2020, 12, 22, 8, 20, 21);

    ProgramMapper testMyMapper() {
        return myMapper(testProgramExpireTimeCalculator());
    }

    ProgramExpireTimeCalculator testProgramExpireTimeCalculator() {
        CurrentDateGetter mock = mock(CurrentDateGetter.class);
        when(mock.now()).thenReturn(DATE);
        return programExpireTimeCalculator(mock);
    }
}
