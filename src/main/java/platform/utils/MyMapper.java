package platform.utils;

import platform.model.Program;
import platform.model.ProgramDto;

public class MyMapper {

    private final MapstructMapper mapper;

    public MyMapper(MapstructMapper mapper) {
        this.mapper = mapper;
    }

    public ProgramDto programToProgramDto(Program program) {
        return mapper.programToProgramDto(program);
    }

    public Program programDtoToProgram(ProgramDto programDto) {
        return mapper.programDtoToProgram(programDto);
    }
}
