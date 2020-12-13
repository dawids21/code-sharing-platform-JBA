package platform.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import platform.model.Program;
import platform.model.ProgramDto;

@Mapper
public interface MapstructMapper {

    @Mapping(source = "created", target = "date", dateFormat = "yyyy-MM-dd hh:mm:ss")
    ProgramDto programToProgramDto(Program program);
}
