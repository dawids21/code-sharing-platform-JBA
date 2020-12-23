package platform.service;

import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import platform.model.ProgramRepository;
import platform.service.model.Program;
import platform.utils.MapstructMapper;
import platform.utils.MyMapper;
import platform.utils.ProgramExpireTimeCalculator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestServiceConfig extends ServiceConfig {

    static final LocalDateTime DATE = LocalDateTime.of(2020, 12, 22, 8, 20, 21);

    public ProgramService testProgramService(ProgramDateSetter programDateSetter,
                                             List<Program> programs) {
        ProgramRepository programRepository = configureDatabaseMock(programs);
        MyMapper mapper =
                 new MyMapper(Mappers.getMapper(MapstructMapper.class), testCalculator());
        return programService(programDateSetter, programRepository, mapper);
    }

    private ProgramExpireTimeCalculator testCalculator() {
        ProgramExpireTimeCalculator calculator = mock(ProgramExpireTimeCalculator.class);
        when(calculator.dateAfterSeconds(Mockito.anyInt())).thenReturn(
                 DATE.plusSeconds(10));
        when(calculator.secondsRemain(Mockito.any(LocalDateTime.class))).thenReturn(10);
        return calculator;
    }

    private ProgramRepository configureDatabaseMock(List<Program> programs) {
        ProgramRepository programRepository = mock(ProgramRepository.class);
        when(programRepository.save(Mockito.any(Program.class))).then(invocation -> {
            Program program = invocation.getArgument(0, Program.class);
            program.setId(programs.size());
            programs.add(invocation.getArgument(0, Program.class));
            return program;
        });
        when(programRepository.findById(Mockito.anyLong())).then(invocation -> {
            long id = invocation.getArgument(0);
            if (id < 0 || id >= programs.size()) {
                return Optional.empty();
            }
            return Optional.of(programs.get((int) id));
        });
        when(programRepository.findAllByRestrictedFalseOrderByCreatedDesc(
                 Mockito.any(Pageable.class))).then(invocation -> {
            Pageable pageable = invocation.getArgument(0, Pageable.class);
            List<Program> result = new ArrayList<>(programs);
            result.sort(Comparator.comparing(Program::getCreated)
                                  .reversed());
            int start = (int) pageable.getOffset();
            int end = (Math.min(start + pageable.getPageSize(), programs.size()));
            return new PageImpl<>(result.subList(start, end));
        });
        return programRepository;
    }
}
