package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeCheckerTest {

    PalindromeChecker checker = new PalindromeChecker();

    @Test
    void testIsPalindromePerformance(){
        assertTimeout(Duration.ofMillis(50), () -> {
            checker.isPalindrome("А роза упала на лапу Азора");
        }, "Метод isPalindrome работает слишком медленно!");
    }
}