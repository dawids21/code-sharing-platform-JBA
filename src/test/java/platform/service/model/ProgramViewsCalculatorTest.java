package platform.service.model;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProgramViewsCalculatorTest extends ModelTestBase {

    private final ProgramViewsCalculator programViewsCalculator =
             new ProgramViewsCalculator();

    @Test
    void should_return_how_many_more_views_are_allowed() {
        Program program = testProgram();

        int result = programViewsCalculator.calculate(program);

        assertThat(result).isEqualTo(program.getViewsAllowed() - program.getCountViews());
    }
}