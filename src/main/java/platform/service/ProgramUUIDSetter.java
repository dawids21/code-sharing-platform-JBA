package platform.service;

import platform.service.model.Program;

public class ProgramUUIDSetter {

    private final RandomUUIDGenerator randomUUIDGenerator;

    public ProgramUUIDSetter(RandomUUIDGenerator randomUUIDGenerator) {
        this.randomUUIDGenerator = randomUUIDGenerator;
    }

    public void setUUID(Program program) {
        program.setId(randomUUIDGenerator.generate());
    }
}
