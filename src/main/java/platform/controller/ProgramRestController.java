package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(path = "/code/new", consumes = "application/json")
    public String saveProgram(@RequestBody ProgramDto programDto) {
        programService.setProgram(programDto);
        return "{}";
    }
}
