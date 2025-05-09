package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {
    NumberUtils utils = new NumberUtils();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, -2, 36})
    void testEvenNumbers(int numbers){
        assertTrue(utils.isEven(numbers));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 7, -5, 37})
    void testOddNumbers(int numbers){
        assertFalse(utils.isEven(numbers));
    }
}