package platform.service;

import platform.service.model.Program;
import platform.service.model.ProgramDateFormatter;

class ProgramDateSetterImpl implements ProgramDateSetter {

    private final CurrentDateGetter currentDateGetter;
    private final ProgramDateFormatter programDateFormatter;

    public ProgramDateSetterImpl(CurrentDateGetter currentDateGetter) {
        this.currentDateGetter = currentDateGetter;
        programDateFormatter = new ProgramDateFormatter();
    }

    public void setCreated(Program program) {
        program.setCreated(currentDateGetter.now());
    }
}