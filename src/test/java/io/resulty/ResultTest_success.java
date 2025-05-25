package io.resulty;

import org.junit.jupiter.api.Test;

class ResultTest_success {

    @Test
    void test_success() throws Exception {
        var r = Result.success("success");
        switch (r) {
            case Result.Success(String value) -> value.toLowerCase();
            case Result.Error(Exception e) -> throw e;
        }
    }
}