package platform.service;

import platform.service.model.Program;
import platform.service.model.ProgramDto;

import java.time.LocalDateTime;
import java.util.UUID;

class ServiceTestBase {

    static final LocalDateTime DATE = LocalDateTime.of(2020, 12, 22, 8, 20, 21);
    static final String DATE_STRING = "2020-12-22 08:20:21";
    static final UUID TEST_UUID = UUID.fromString("e6780274-c41c-4ab4-bde6-b32c18b4c489");

    // TODO put common methods here
    protected Program testProgram() {
        return new Program(TEST_UUID, "main()", DATE, DATE.plusSeconds(10), 10, true);
    }

    protected ProgramDto testProgramDto() {
        return new ProgramDto("main()", DATE_STRING, 10, 10);
    }

}
