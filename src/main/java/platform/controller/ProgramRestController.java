package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.model.ProgramDto;
import platform.service.ProgramService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class ProgramRestController {

    private final ProgramService programService;

    @Autowired
    public ProgramRestController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping(path = "/code/{id}")
    public ProgramDto getProgram(@PathVariable int id) {
        return programService.getProgram(id);
    }

    @GetMapping(path = "/code/latest")
    public List<ProgramDto> getLatestPrograms() {
        return programService.getLastPrograms(10);
    }

    @PostMapping(path = "/code/new", consumes = "application/json")
    public Map<String, String> saveProgram(@RequestBody ProgramDto programDto) {
        int id = programService.addProgram(programDto);
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        return map;
    }
}
