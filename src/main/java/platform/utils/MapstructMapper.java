package platform.utils;

import org.mapstruct.Mapper;
import platform.model.Program;
import platform.model.ProgramDto;

@Mapper
public interface MapstructMapper {

    ProgramDto programToProgramDto(Program program);
}
