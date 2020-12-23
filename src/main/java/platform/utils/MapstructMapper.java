package platform.utils;

import org.mapstruct.Mapper;
import platform.service.ProgramService;
import platform.service.model.Program;
import platform.service.model.ProgramDto;

import java.time.LocalDateTime;

@Mapper
public interface MapstructMapper {

    default ProgramDto programToProgramDto(Program program,
                                           ProgramExpireTimeCalculator calculator) {
        String code = program.getCode();
        String date = program.getCreated()
                             .format(ProgramService.DATE_TIME_FORMATTER);
        int time = calculator.secondsRemain(program.getValidUntil());

        return new ProgramDto(code, date, time);
    }

    default Program programDtoToProgram(ProgramDto programDto,
                                        ProgramExpireTimeCalculator calculator) {
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
