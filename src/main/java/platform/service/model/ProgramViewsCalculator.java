package platform.service.model;

public class ProgramViewsCalculator {

    public int calculate(Program program) {
        return program.getViewsAllowed() - program.getCountViews();
    }
}
