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
    private final ProgramViewsReducer programViewsReducer;
    private final RestrictionChecker restrictionChecker;

    public ProgramService(ProgramDateSetter programDateSetter,
                          ProgramRepository programRepository, ProgramMapper mapper,
                          ProgramViewsReducer programViewsReducer,
                          RestrictionChecker restrictionChecker) {
        this.programRepository = programRepository;
        this.programDateSetter = programDateSetter;
        this.mapper = mapper;
        this.programViewsReducer = programViewsReducer;
        this.restrictionChecker = restrictionChecker;
    }

    public ProgramDto getProgram(UUID id) {
        Program program = programRepository.findById(id)
                                           .orElseThrow(() -> new ResponseStatusException(
                                                    HttpStatus.NOT_FOUND,
                                                    "Id does not exists"));
        program.setCountViews(program.getCountViews() + 1);
        if (RestrictionChecker.STATUS.INVALID.equals(restrictionChecker.check(program))) {
            programRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                              "Requested program is no longer available");
        }
        programRepository.save(program);
        return mapper.programToProgramDto(program);
    }

    public UUID addProgram(ProgramDto programDto) {
        Program program = mapper.programDtoToProgram(programDto);
        programDateSetter.setCreated(program);
        program = programRepository.save(program);
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
