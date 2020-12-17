package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import platform.service.ProgramService;

import java.util.List;

@Controller
public class ProgramController {

    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping(path = "/code", produces = "text/html")
    public ModelAndView getProgram() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("programs", List.of(programService.getProgram()));
        return modelAndView;
    }

    @GetMapping(path = "/code/new", produces = "text/html")
    public ModelAndView sendNewProgram() {
        return new ModelAndView("new_code");
    }
}
