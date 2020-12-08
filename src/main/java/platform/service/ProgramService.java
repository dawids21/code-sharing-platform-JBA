package platform.service;

import platform.model.ProgramDto;

public class ProgramService {

    public ProgramService() {
    }

    public ProgramDto getProgram() {
        return new ProgramDto(
                 "public class Main {\n public static void main(String[] args) {\n  System.out.println(\"Hello World\");\n }\n}");
    }
}
