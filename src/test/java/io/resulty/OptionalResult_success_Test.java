package io.resulty;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class OptionalResult_success_Test {
    @Test
    void optionalSuccess() {
        var r = OptionalResult.success(Optional.of("value"));
        assertThat(r).isEqualTo(OptionalResult.success("value"));
    }

    @Test
    void optionalEmpty() {
        var r = OptionalResult.success(Optional.empty());
        assertThat(r).isEqualTo(OptionalResult.empty());
    }

    @Test
    void test_flatmap_value_With_Result() {
        Result<String> s1 = OptionalResult.success("s")
                .flatMap((String s) -> Result.success(s.toLowerCase()));
        switch (s1) {
            case Result.Error<String> v -> {
                fail();
            }
            case Result.Success<String> v -> {
            }
        }
    }
}
