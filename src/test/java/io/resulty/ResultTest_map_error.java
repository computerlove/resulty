package io.resulty;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest_map_error {

    @Test
    void test_map_success() throws Exception {
        String value = "success";
        Exception exception = new Exception("Failed value");
        var r = Result.<String>error(exception)
                .map(String::length);
        assertThat(r).isEqualTo(Result.error(exception));

    }
}