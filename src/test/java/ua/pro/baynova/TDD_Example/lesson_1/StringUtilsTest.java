package ua.pro.baynova.TDD_Example.lesson_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testIsPalindromeTrue(){
        assertTrue(StringUtils.isPalindrome("level"));
    }

    @Test
    void testIsPalindromeFalse(){
        assertFalse(StringUtils.isPalindrome("java"));
    }

    @Test
    void testIsPalindromeCaseSensitive(){
        assertTrue(StringUtils.isPalindrome("Deed"));
    }
}
