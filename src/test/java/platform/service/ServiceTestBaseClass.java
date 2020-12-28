package platform.service;

import platform.service.model.Program;
import platform.service.model.ProgramDto;

class ServiceTestBaseClass {

    // TODO put common methods here
    protected Program testProgram() {
        return new Program(TestServiceConfig.TEST_UUID, "main()", TestServiceConfig.DATE,
                           TestServiceConfig.DATE.plusSeconds(10), 10, true);
    }

    protected ProgramDto testProgramDto() {
        return new ProgramDto("main()", TestServiceConfig.DATE_STRING, 10, 10);
    }

}
