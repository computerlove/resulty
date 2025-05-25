package io.resulty;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultyTest_map_success {

    @Test
    void test_map_success() throws Exception {
        String value = "success";
        var r = Resulty.success(value)
                .map(String::length);
        assertThat(r).isEqualTo(Resulty.success(value.length()));

    }
}