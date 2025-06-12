package ua.pro.baynova.TDD_Example.lesson_2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {

    @Test
    void testSumEvenNumbers_basic(){
        List<Integer> input = List.of(2,4,6);
        int result = NumberUtils.sumEvenNumbers(input);
        assertEquals(12, result);
    }

    @Test
    void testSumEvenNumbers_withOddNumbers(){
        List<Integer> input = List.of(1,2,3,4,5);
        int result = NumberUtils.sumEvenNumbers(input);
        assertEquals(6, result);
    }

    @Test
    void testSumEvenNumbers_emptyList(){
        assertEquals(0, NumberUtils.sumEvenNumbers(List.of()));
    }

    @Test
    void testSumEvenNumbers_onlyOdd(){
        assertEquals(0, NumberUtils.sumEvenNumbers(List.of(1,3,5)));
    }

    @Test
    void testSumEvenNumbers_mixed(){
        assertEquals(12, NumberUtils.sumEvenNumbers(List.of(1,2,3,4,5,6)));
    }
}
