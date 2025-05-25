package io.resulty;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Result_map_success_Test {

    @Test
    void test_map_success() throws Exception {
        String value = "success";
        var r = Result.success(value)
                .map(String::length);
        assertThat(r).isEqualTo(Result.success(value.length()));

    }
}