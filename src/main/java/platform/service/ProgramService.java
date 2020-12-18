package platform.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import platform.model.ProgramDto;
import platform.model.ProgramRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProgramService {

    public static final DateTimeFormatter DATE_TIME_FORMATTER =
             DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final List<ProgramDto> programDtos;
    private final ProgramDateSetter programDateSetter;
    private final ProgramRepository programRepository;

    public ProgramService(ProgramDateSetter programDateSetter,
                          ProgramRepository programRepository) {
        this.programRepository = programRepository;
        programDtos = new ArrayList<>();
        this.programDateSetter = programDateSetter;
    }

    public ProgramDto getProgram(int n) {
        if (n >= programDtos.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Id does not exits");
        }
        return programDtos.get(n);
    }

    public int addProgram(ProgramDto program) {
        programDateSetter.setDate(program);
        programDtos.add(program);
        return programDtos.size() - 1;
    }

    public List<ProgramDto> getLastPrograms(int n) {
        List<ProgramDto> result = new ArrayList<>();
        for (int i = programDtos.size() - Math.min(programDtos.size(), n);
                 i < programDtos.size(); i++) {
            result.add(programDtos.get(i));
        }
        result.sort(Comparator.comparing(
                 o -> LocalDateTime.parse(((ProgramDto) o).getDate(),
                                          DATE_TIME_FORMATTER))
                              .reversed());
        return result;
    }
}
