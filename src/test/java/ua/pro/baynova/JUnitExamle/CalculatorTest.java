package ua.pro.baynova.JUnitExamle;

import junit.framework.TestCase;

public class CalculatorTest extends TestCase {

    public void testAdd() {
        Calculator calc = new Calculator();
        int result = calc.add(10, 12);
        assertEquals(22, result);
    }
}