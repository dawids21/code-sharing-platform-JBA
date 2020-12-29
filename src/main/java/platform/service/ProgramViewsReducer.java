package platform.service;

import platform.service.model.Program;

public class ProgramViewsReducer {

    public Program reduce(Program program) {
        if (program.getCountViews() != null && program.isRestricted() &&
            program.getCountViews() >= 0) {
            program.setCountViews(program.getCountViews() - 1);
        }
        return program;
    }
}
