package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.service.ProgramService;
import platform.service.model.ProgramDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class ProgramRestController {

    private final ProgramService programService;

    @Autowired
    public ProgramRestController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping(path = "/code/{id}")
    public ProgramDto getProgram(@PathVariable UUID id) {
        return programService.getProgram(id);
    }

    @GetMapping(path = "/code/latest")
    public List<ProgramDto> getLatestPrograms() {
        return programService.getLastPrograms(10);
    }

    @PostMapping(path = "/code/new", consumes = "application/json")
    public Map<String, String> saveProgram(@RequestBody ProgramDto programDto) {
        UUID id = programService.addProgram(programDto);
        Map<String, String> map = new HashMap<>();
        map.put("id", id.toString());
        return map;
    }
}
