package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class LoginFilterTest {
    LoginFilter filter = new LoginFilter();

    @Test
    void testFilterValidLogins(){
        List<String> inputLogins = Arrays.asList(
                "Anna",
                "123Login",
                "L0gin!",
                "Bob123",
                "ab",
                null,
                "ThisIsTooLong123",
                "Mary007"

        );

        List<String> expectedValid = Arrays.asList(
                "Anna",
                "Bob123",
                "Mary007"
        );

        assertIterableEquals(expectedValid, filter.filterValidLogins(inputLogins));
    }
}
