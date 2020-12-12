package platform.service;

import platform.model.ProgramDto;

public class ProgramService {

    private ProgramDto programDto;

    public ProgramService() {
        programDto = new ProgramDto(
                 "public class Main {\n public static void main(String[] args) {\n  System.out.println(\"Hello World\");\n }\n}");
    }

    public ProgramDto getProgram() {
        return programDto;
    }
}
