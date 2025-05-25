package io.resulty;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultyTest_flatmap_success {

    @Test
    void test_flatmap_success_with_success() throws Exception {
        String value = "success";
        var r = Resulty.success(value)
                .flatMap(s -> Resulty.success(value.length()));
        assertThat(r).isEqualTo(Resulty.success(value.length()));
    }


    @Test
    void test_flatmap_success_with_failure() throws Exception {
        String value = "success";
        var exception = new Exception("not success");
        var r = Resulty.success(value)
                .flatMap(s -> Resulty.error(exception));
        assertThat(r).isEqualTo(Resulty.error(exception));
    }
}