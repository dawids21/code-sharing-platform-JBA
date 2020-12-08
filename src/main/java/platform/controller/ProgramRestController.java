package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.model.ProgramDto;
import platform.service.ProgramService;

@RestController
@RequestMapping(path = "/api")
public class ProgramRestController {

    private final ProgramService programService;

    @Autowired
    public ProgramRestController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping(path = "/code")
    public ProgramDto getProgram() {
        return programService.getProgram();
    }
}
