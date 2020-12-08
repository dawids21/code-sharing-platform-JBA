package platform.services;

import platform.model.ProgramDto;

public class ProgramService {

    public ProgramService() {
    }

    public ProgramDto getProgram() {
        return new ProgramDto(
                 "public class Main { public static void main(String[] args) { System.out.println(\"Hello World\"); } }");
    }
}
