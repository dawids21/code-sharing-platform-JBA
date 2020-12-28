package platform.service;

import platform.service.model.Program;

public class ProgramViewsReducer {

    public Program reduce(Program program) {
        if (program.isRestricted() && program.getViews() > 0) {
            program.setViews(program.getViews() - 1);
        }
        return program;
    }
}
