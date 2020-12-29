package platform.service.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProgramExpireTimeCalculatorTest extends ModelTestBase {

    ProgramExpireTimeCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new TestModelConfig().testProgramExpireTimeCalculator();
    }

    @Test
    void should_return_how_much_seconds_remain_to_given_date() {
        LocalDateTime date = DATE.plusSeconds(23);
        int remainingSeconds = calculator.secondsRemain(date);

        assertThat(remainingSeconds).isEqualTo(23);
    }

    @Test
    void should_return_date_after_given_seconds() {
        LocalDateTime afterTenSeconds = calculator.dateAfterSeconds(10);
        LocalDateTime expected = DATE.plusSeconds(10);

        assertThat(afterTenSeconds).isEqualTo(expected);
    }
}