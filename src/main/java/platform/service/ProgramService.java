package platform.service;

import platform.model.ProgramDto;

import java.time.LocalDateTime;

public class ProgramService {

    private ProgramDto programDto;

    public ProgramService() {
        programDto = new ProgramDto(
                 "public class Main {\n public static void main(String[] args) {\n  System.out.println(\"Hello World\");\n }\n}",
                 LocalDateTime.now());
    }

    public ProgramDto getProgram() {
        return programDto;
    }

    public void setProgram(ProgramDto program) {
        program.setDate(LocalDateTime.now());
        programDto = program;
    }
}
