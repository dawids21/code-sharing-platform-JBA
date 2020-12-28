package platform.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import platform.service.model.Program;
import platform.service.model.ProgramDto;
import platform.service.model.ProgramMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProgramService {

    private final ProgramDateSetter programDateSetter;
    private final ProgramRepository programRepository;
    private final ProgramMapper mapper;
    private final RandomUUIDGenerator randomUUIDGenerator;

    public ProgramService(ProgramDateSetter programDateSetter,
                          ProgramRepository programRepository, ProgramMapper mapper,
                          RandomUUIDGenerator randomUUIDGenerator) {
        this.programRepository = programRepository;
        this.programDateSetter = programDateSetter;
        this.mapper = mapper;
        this.randomUUIDGenerator = randomUUIDGenerator;
    }

    public ProgramDto getProgram(UUID id) {
        return programRepository.findById(id)
                                .map(mapper::programToProgramDto)
                                .orElseThrow(() -> new ResponseStatusException(
                                         HttpStatus.NOT_FOUND, "Id does not exists"));
    }

    public UUID addProgram(ProgramDto programDto) {
        programDateSetter.setDate(programDto);
        Program program = programRepository.save(mapper.programDtoToProgram(programDto));
        return program.getId();
    }

    public List<ProgramDto> getLastPrograms(int n) {
        Pageable pageable = PageRequest.of(0, n);
        return programRepository.findNotRestricted(pageable)
                                .stream()
                                .map(mapper::programToProgramDto)
                                .collect(Collectors.toList());
    }
}
