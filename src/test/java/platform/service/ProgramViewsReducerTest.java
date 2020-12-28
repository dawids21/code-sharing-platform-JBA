package platform.service;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProgramViewsReducerTest {

    @Test
    void should_reduce_views_by_one_if_restricted() {
        //TODO implement should_reduce_views_by_one_if_restricted
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    void should_reduce_views_only_when_they_are_greater_than_zero(int views) {
        //TODO implement should_reduce_views_only_when_they_are_greater_than_zero
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void should_not_reduce_views_when_program_is_not_restricted() {
        //TODO implement should_not_reduce_views_when_program_is_not_restricted
        throw new UnsupportedOperationException("Not implemented yet");
    }
}