package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(path = "/code/{id}", produces = "text/html")
    public ModelAndView getProgram(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("programs", List.of(programService.getProgram(id)));
        return modelAndView;
    }

    @GetMapping(path = "/code/latest")
    public ModelAndView getLatestPrograms() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("programs", programService.getLastPrograms(10));
        return modelAndView;
    }

    @GetMapping(path = "/code/new", produces = "text/html")
    public ModelAndView sendNewProgram() {
        return new ModelAndView("new_code");
    }
}
