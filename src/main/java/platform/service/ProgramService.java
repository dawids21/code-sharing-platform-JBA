package platform.service;

import platform.model.ProgramDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProgramService {

    public static final DateTimeFormatter DATE_TIME_FORMATTER =
             DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Clock clock;
    private ProgramDto programDto;

    public ProgramService(Clock clock) {
        this.clock = clock;
        programDto = new ProgramDto(
                 "public class Main {\n public static void main(String[] args) {\n  System.out.println(\"Hello World\");\n }\n}",
                 LocalDateTime.now(clock)
                              .format(DATE_TIME_FORMATTER));
    }

    public ProgramDto getProgram() {
        return programDto;
    }

    public void setProgram(ProgramDto program) {
        LocalDateTime now = LocalDateTime.now(clock);
        program.setDate(now.format(DATE_TIME_FORMATTER));
        programDto = program;
    }
}
