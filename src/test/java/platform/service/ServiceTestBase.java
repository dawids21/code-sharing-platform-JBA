package platform.service;

import platform.service.model.Program;
import platform.service.model.ProgramDto;

import java.time.LocalDateTime;
import java.util.UUID;

class ServiceTestBase {

    protected static final LocalDateTime DATE = LocalDateTime.of(2020, 12, 22, 8, 20, 21);
    protected static final String DATE_STRING = "2020-12-22 08:20:21";
    protected static final UUID VALID_PROGRAM_UUID =
             UUID.fromString("e6780274-c41c-4ab4-bde6-b32c18b4c489");
    protected static final UUID INVALID_PROGRAM_UUID =
             UUID.fromString("e6780274-c41c-4ab4-bde6-b32c18b23489");

    protected Program testValidProgram() {
        return new Program(VALID_PROGRAM_UUID, "main()", DATE, DATE.plusSeconds(10), 10,
                           true);
    }
    }

    protected ProgramDto testProgramDto() {
        return new ProgramDto("main()", DATE_STRING, 10, 10);
    }
}
