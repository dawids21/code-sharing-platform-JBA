package platform.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import platform.model.ProgramRepository;
import platform.service.model.MyMapper;
import platform.service.model.Program;
import platform.service.model.ProgramDto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramService {

    public static final DateTimeFormatter DATE_TIME_FORMATTER =
             DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final ProgramDateSetter programDateSetter;
    private final ProgramRepository programRepository;
    private final MyMapper mapper;

    public ProgramService(ProgramDateSetter programDateSetter,
                          ProgramRepository programRepository, MyMapper mapper) {
        this.programRepository = programRepository;
        this.programDateSetter = programDateSetter;
        this.mapper = mapper;
    }

    public ProgramDto getProgram(long id) {
        return programRepository.findById(id)
                                .map(mapper::programToProgramDto)
                                .orElseThrow(() -> new ResponseStatusException(
                                         HttpStatus.NOT_FOUND, "Id does not exists"));
    }

    public long addProgram(ProgramDto programDto) {
        programDateSetter.setDate(programDto);
        Program program = programRepository.save(mapper.programDtoToProgram(programDto));
        return program.getId();
    }

    public List<ProgramDto> getLastPrograms(int n) {
        Pageable pageable = PageRequest.of(0, n);
        return programRepository.findAllByRestrictedFalseOrderByCreatedDesc(pageable)
                                .stream()
                                .map(mapper::programToProgramDto)
                                .collect(Collectors.toList());
    }
}
