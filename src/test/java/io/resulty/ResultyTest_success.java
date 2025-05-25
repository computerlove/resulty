package io.resulty;

import org.junit.jupiter.api.Test;

class ResultyTest_success {

    @Test
    void test_success() throws Exception {
        var r = Resulty.success("success");
        switch (r) {
            case Resulty.Success(String value) -> value.toLowerCase();
            case Resulty.Error(Exception e) -> throw e;
        }
    }
}