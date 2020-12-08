package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import platform.service.ProgramService;

@Controller
public class ProgramController {

    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping(path = "/code", produces = "text/html")
    public ResponseEntity<String> getProgram() {
        return ResponseEntity.ok("<html><head><title>Code</title></head><body><pre>" +
                                 programService.getProgram()
                                               .getCode() + "</pre></body></html>");
    }
}
