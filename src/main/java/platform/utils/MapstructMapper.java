package platform.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import platform.model.Program;
import platform.model.ProgramDto;

@Mapper
public interface MapstructMapper {

    @Mapping(source = "created", target = "date", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ProgramDto programToProgramDto(Program program);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "date", target = "created", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Program programDtoToProgram(ProgramDto programDto);
}
