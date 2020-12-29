package platform.service.model;

import platform.service.CurrentDateGetter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestModelConfig extends ModelConfig {

    ProgramMapper testMyMapper() {
        return myMapper(testProgramExpireTimeCalculator());
    }

    ProgramExpireTimeCalculator testProgramExpireTimeCalculator() {
        CurrentDateGetter mock = mock(CurrentDateGetter.class);
        when(mock.now()).thenReturn(ModelTestBase.DATE);
        return programExpireTimeCalculator(mock);
    }
}
