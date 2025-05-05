package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    void testAdd() {
        int result = calc.add(10, 12);
        assertEquals(22, result);
    }

    @Test
    void testSquare() {
        assertEquals(25, calc.square(5));
        assertEquals(0, calc.square(0));
        assertEquals(16, calc.square(-4));
    }
}