package platform.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import platform.utils.ProgramExpireTimeCalculator;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProgramExpireTimeCalculatorTest {


    private static final LocalDateTime DATE = LocalDateTime.of(2020, 12, 21, 21, 22, 39);
    ProgramExpireTimeCalculator calculator;

    @BeforeEach
    void setUp() {
        Clock fixedClock = Clock.fixed(DATE.atZone(ZoneId.systemDefault())
                                           .toInstant(), ZoneId.systemDefault());
        calculator = new ProgramExpireTimeCalculator(fixedClock);
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