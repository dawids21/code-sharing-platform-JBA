package platform.service.model;

import platform.service.ProgramService;
import platform.utils.ProgramExpireTimeCalculator;

import java.time.LocalDateTime;

public class ProgramMapper {

    private final ProgramExpireTimeCalculator calculator;

    public ProgramMapper(ProgramExpireTimeCalculator calculator) {
        this.calculator = calculator;
    }

    public ProgramDto programToProgramDto(Program program) {
        String code = program.getCode();
        String date = program.getCreated()
                             .format(ProgramService.DATE_TIME_FORMATTER);
        int time = calculator.secondsRemain(program.getValidUntil());

        return new ProgramDto(code, date, time);
    }

    public Program programDtoToProgram(ProgramDto programDto) {
        String code = programDto.getCode();
        LocalDateTime created = LocalDateTime.parse(programDto.getDate(),
                                                    ProgramService.DATE_TIME_FORMATTER);
        LocalDateTime validUntil = null;
        boolean restricted = false;
        if (programDto.getTime() > 0) {
            validUntil = calculator.dateAfterSeconds(programDto.getTime())
                                   .withNano(0);
            restricted = true;
        }

        Program target = new Program();
        target.setCode(code);
        target.setCreated(created);
        target.setValidUntil(validUntil);
        target.setRestricted(restricted);
        return target;
    }
}
