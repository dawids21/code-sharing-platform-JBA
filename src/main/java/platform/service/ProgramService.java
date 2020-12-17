package platform.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import platform.model.ProgramDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProgramService {

    public static final DateTimeFormatter DATE_TIME_FORMATTER =
             DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Clock clock;
    private final List<ProgramDto> programDtos;

    public ProgramService(Clock clock) {
        this.clock = clock;
        programDtos = new ArrayList<>();
    }

    public ProgramDto getProgram(int n) {
        if (n >= programDtos.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Id does not exits");
        }
        return programDtos.get(n);
    }

    public int addProgram(ProgramDto program) {
        LocalDateTime now = LocalDateTime.now(clock);
        program.setDate(now.format(DATE_TIME_FORMATTER));
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
