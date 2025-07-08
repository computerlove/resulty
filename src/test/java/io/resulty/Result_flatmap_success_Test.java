package io.resulty;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class Result_flatmap_success_Test {

    @Test
    void test_flatmap_success_with_success() throws Exception {
        String value = "success";
        Result<String> success = Result.success(value);

        Result<Integer> r = success
                .flatMap( (String s1) -> Result.success(s1.length()));

        OptionalResult<Integer> r2 = success
                .flatMap( (String s) -> OptionalResult.success(s.length()));

        switch (r2) {
            case OptionalResult.Empty<Integer> v -> {
                fail();
            }
            case OptionalResult.Error<Integer> v -> {
                fail();
            }
            case OptionalResult.Value(Integer v) -> {

            }
        }
    }

    @Test
    void test_flatmap_success_with_failure() throws Exception {
        String value = "success";
        var exception = new Exception("not success");
        var r = Result.success(value)
                .flatMap((String s) -> Result.error(exception));
        assertThat(r).isEqualTo(Result.error(exception));
    }

    @Test
    void test_flatmap_success_with_OptionalResult_error() throws Exception {
        String value = "success";
        var exception = new Exception("not success");

        var r = Result.success(value)
                .flatMap((String s) -> OptionalResult.error(exception));
        assertThat(r).isEqualTo(OptionalResult.error(exception));
    }

    @Test
    void test_flatmap_success_with_OptionalResult_empty() throws Exception {
        String value = "success";

        var r = Result.success(value)
                .flatMap((String s) -> OptionalResult.empty());
        assertThat(r).isEqualTo(OptionalResult.empty());
    }

    @Test
    void test_flatmap_error_with_Result() {
        Result<String> r = Result.<String>error(new Exception())
                .flatMap((String s) -> Result.success("succes"));
        switch (r) {
            case Result.Error<String> v -> {
            }
            case Result.Success<String> v -> {
                fail();
            }
        }
    }
    @Test
    void test_flatmap_error_with_OptionalResult() {
        OptionalResult<String> r = Result.<String>error(new Exception())
                .flatMap((String s) -> OptionalResult.success("succes"));
        switch (r) {
            case OptionalResult.Empty<String> v -> {
                fail();
            }
            case OptionalResult.Error<String> v -> {
            }
            case OptionalResult.Value<String> v -> {
                fail();
            }
        }
    }
}