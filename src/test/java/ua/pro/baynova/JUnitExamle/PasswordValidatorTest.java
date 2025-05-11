package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
    PasswordValidator validator = new PasswordValidator();

    @DisplayName("Позитивный тест: надёжные пароли ")
    @ParameterizedTest
    @ValueSource(strings = {"Abc12345", "PassWord9", "A1b2c3d4"})

    void testStrongPasswords(String password){
        assertTrue(validator.isStrongPassword(password));
    }

    @DisplayName("Негативный тест: ненадёжные пароли")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "12345678", "ABCDEFGH", "abcdEFGH", "", "pass1234", "PASSWORD123"})

    void testWeakPasswords(String password){
        assertFalse(validator.isStrongPassword(password));
    }

    @Test
    @DisplayName("Обработка null пароля")
    void testNullPassword(){
        assertFalse(validator.isStrongPassword(null));
    }



}