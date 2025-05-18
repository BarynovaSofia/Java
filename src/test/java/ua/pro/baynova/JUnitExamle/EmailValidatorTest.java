package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {
    EmailValidator validator = new EmailValidator();

    @ParameterizedTest
    @CsvFileSource(resources = "/emails.csv", numLinesToSkip = 0)
    void testEmailValidation(String email, boolean expected){
        assertEquals(expected, validator.isValidEmail(email));
    }
}