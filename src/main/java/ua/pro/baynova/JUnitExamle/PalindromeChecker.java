package ua.pro.baynova.JUnitExamle;

public class PalindromeChecker {

    public boolean isPalindrome(String text){
        if (text == null) return false;
        String cleaned = text.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }
}
