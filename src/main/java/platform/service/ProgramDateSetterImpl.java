package platform.service;

import platform.service.model.ProgramDateFormatter;
import platform.service.model.ProgramDto;

class ProgramDateSetterImpl implements ProgramDateSetter {

    private final CurrentDateGetter currentDateGetter;
    private final ProgramDateFormatter programDateFormatter;

    public ProgramDateSetterImpl(CurrentDateGetter currentDateGetter) {
        this.currentDateGetter = currentDateGetter;
        programDateFormatter = new ProgramDateFormatter();
    }

    public void setCreated(ProgramDto program) {
        program.setDate(programDateFormatter.toString(currentDateGetter.now()));
    }
}