package platform.service.model;

import java.time.LocalDateTime;

public class ProgramMapper {

    private final ProgramDateFormatter dateFormatter;
    private final ProgramExpireTimeCalculator calculator;

    public ProgramMapper(ProgramExpireTimeCalculator calculator) {
        this.calculator = calculator;
        dateFormatter = new ProgramDateFormatter();
    }

    public ProgramDto programToProgramDto(Program program) {
        String code = program.getCode();
        String date = dateFormatter.toString(program.getCreated());
        int time = calculator.secondsRemain(program.getValidUntil());

        return new ProgramDto(code, date, time);
    }

    public Program programDtoToProgram(ProgramDto programDto) {
        String code = programDto.getCode();
        LocalDateTime created = dateFormatter.toLocalDateTime(programDto.getDate());
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
