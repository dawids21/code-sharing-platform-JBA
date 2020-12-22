package platform.utils;

import org.mapstruct.Mapper;
import platform.model.Program;
import platform.model.ProgramDto;
import platform.service.ProgramService;

import java.time.LocalDateTime;

@Mapper
public interface MapstructMapper {

    default ProgramDto programToProgramDto(Program program,
                                           ProgramExpireTimeCalculator calculator) {
        String code = program.getCode();
        String date = program.getCreated()
                             .format(ProgramService.DATE_TIME_FORMATTER);
        int remainingSeconds = calculator.secondsRemain(program.getValidUntil());

        return new ProgramDto(code, date, remainingSeconds);
    }

    default Program programDtoToProgram(ProgramDto programDto,
                                        ProgramExpireTimeCalculator calculator) {
        String code = programDto.getCode();
        LocalDateTime created = LocalDateTime.parse(programDto.getDate(),
                                                    ProgramService.DATE_TIME_FORMATTER);
        LocalDateTime validUntil =
                 calculator.dateAfterSeconds(programDto.getRemainingSeconds());

        Program target = new Program();
        target.setCode(code);
        target.setCreated(created);
        target.setValidUntil(validUntil);
        return target;
    }
}
