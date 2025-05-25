package io.resulty;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Result_flatmap_success_Test {

    @Test
    void test_flatmap_success_with_success() throws Exception {
        String value = "success";
        var r = Result.success(value)
                .flatMap(s -> Result.success(value.length()));
        assertThat(r).isEqualTo(Result.success(value.length()));
    }

    @Test
    void test_flatmap_success_with_failure() throws Exception {
        String value = "success";
        var exception = new Exception("not success");
        var r = Result.success(value)
                .flatMap(s -> Result.error(exception));
        assertThat(r).isEqualTo(Result.error(exception));
    }

    @Test
    void test_flatmap_success_with_OptionalResult_error() throws Exception {
        String value = "success";
        var exception = new Exception("not success");

        var r = Result.success(value)
                .flatMap(s -> OptionalResult.error(exception));
        assertThat(r).isEqualTo(OptionalResult.error(exception));
    }

    @Test
    void test_flatmap_success_with_OptionalResult_empty() throws Exception {
        String value = "success";

        var r = Result.success(value)
                .flatMap(s -> OptionalResult.empty());
        assertThat(r).isEqualTo(OptionalResult.empty());
    }
}