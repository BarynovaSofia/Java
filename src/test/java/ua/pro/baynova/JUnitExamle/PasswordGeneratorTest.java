package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {
    PasswordGenerator generator = new PasswordGenerator();

    @RepeatedTest(5)
    void testGeneratedPasswordValidity(){
        int length = 10;
        String password = generator.generatePassword(length);

        assertEquals(length, password.length(), "Пароль должен быть длиной 10 символов");

        boolean hasUpper = false;
        boolean hasLower = false;

        for (char c : password.toCharArray()){
            assertTrue(
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".indexOf(c) >= 0,
                    "Недопустимый символ: " + c
            );

            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
        }

        assertTrue(hasUpper ,"Пароль должен содержать хотя бы одну заглавную букву");
        assertTrue(hasLower, "Пароль должен содержать хотя бы одну строчную букву");
    }
}