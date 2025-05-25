package io.resulty;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
}
