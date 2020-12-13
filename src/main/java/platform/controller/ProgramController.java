package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import platform.service.ProgramService;
import platform.utils.HtmlCreator;

@Controller
public class ProgramController {

    private final ProgramService programService;
    private final HtmlCreator htmlCreator;

    @Autowired
    public ProgramController(ProgramService programService, HtmlCreator htmlCreator) {
        this.programService = programService;
        this.htmlCreator = htmlCreator;
    }

    @GetMapping(path = "/code", produces = "text/html")
    public ResponseEntity<String> getProgram() {
        return ResponseEntity.ok(htmlCreator.programPage(programService.getProgram()));
    }
}
