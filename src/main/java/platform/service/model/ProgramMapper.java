package platform.service.model;

import java.time.LocalDateTime;

public class ProgramMapper {

    private final ProgramDateFormatter dateFormatter;
    private final ProgramExpireTimeCalculator calculator;
    private final ProgramViewsCalculator viewsCalculator;

    public ProgramMapper(ProgramExpireTimeCalculator calculator) {
        this.calculator = calculator;
        dateFormatter = new ProgramDateFormatter();
        viewsCalculator = new ProgramViewsCalculator();
    }

    public ProgramDto programToProgramDto(Program program) {
        String code = "";
        String date = "";
        int time = 0;
        int views = 0;

        if (program.getCode() != null) {
            code = program.getCode();
        }
        if (program.getCreated() != null) {
            date = dateFormatter.toString(program.getCreated());
        }
        if (program.getValidUntil() != null) {
            time = calculator.secondsRemain(program.getValidUntil());
        }
        if (program.getViewsAllowed() != null) {
            views = viewsCalculator.calculate(program);
        }
        return new ProgramDto(code, date, time, views);
    }

    public Program programDtoToProgram(ProgramDto programDto) {
        String code = "";
        LocalDateTime created = null;
        LocalDateTime validUntil = null;
        Integer countViews = 0;
        Integer viewsAllowed = null;
        boolean restricted = false;
        if (programDto.getCode() != null) {
            code = programDto.getCode();
        }
        if (programDto.getDate() != null) {
            created = dateFormatter.toLocalDateTime(programDto.getDate());
        }
        if (programDto.getTime() > 0) {
            validUntil = calculator.dateAfterSeconds(programDto.getTime())
                                   .withNano(0);
            restricted = true;
        }

        if (programDto.getViews() > 0) {
            viewsAllowed = programDto.getViews();
            restricted = true;
        }

        Program target = new Program();
        target.setCode(code);
        target.setCreated(created);
        target.setValidUntil(validUntil);
        target.setCountViews(countViews);
        target.setViewsAllowed(viewsAllowed);
        target.setRestricted(restricted);
        return target;
    }
}
