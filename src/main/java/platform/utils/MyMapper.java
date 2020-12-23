package platform.utils;

import platform.service.model.Program;
import platform.service.model.ProgramDto;

public class MyMapper {

    private final MapstructMapper mapper;
    private final ProgramExpireTimeCalculator calculator;

    public MyMapper(MapstructMapper mapper, ProgramExpireTimeCalculator calculator) {
        this.mapper = mapper;
        this.calculator = calculator;
    }

    public ProgramDto programToProgramDto(Program program) {
        return mapper.programToProgramDto(program, calculator);
    }

    public Program programDtoToProgram(ProgramDto programDto) {
        return mapper.programDtoToProgram(programDto, calculator);
    }
}
