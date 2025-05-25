package io.resulty;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Result_map_error_Test {

    @Test
    void test_map_success() throws Exception {
        String value = "success";
        Exception exception = new Exception("Failed value");
        var r = Result.<String>error(exception)
                .map(String::length);
        assertThat(r).isEqualTo(Result.error(exception));

    }
}