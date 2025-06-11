package ua.pro.baynova.TDD_Example.lesson_1;

public class StringUtils {
    public static boolean isPalindrome(String text) {
        text = text.toLowerCase();
        String reversed = new StringBuilder(text).reverse().toString();
        return text.equals(reversed);
    }
}
