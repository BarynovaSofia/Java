package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationValidatorTest {

    RegistrationValidator validator = new RegistrationValidator();

    @Nested
    class NameValidationTests {

        @ParameterizedTest
        @CsvSource({
                "Anna, false",
                "Petro123, true",
                "'', true",
                "Ігор, false"
        })
        void testNameValidation(String name, boolean shouldThrow) {
            if (shouldThrow) {
                assertThrows(IllegalArgumentException.class, () -> validator.validateName(name));
            } else {
                assertDoesNotThrow(() -> validator.validateName(name));
            }
        }
    }

    @Nested
    class EmailValidationTests{

        @ParameterizedTest
        @CsvSource({
                "user@example.com, false",
                "no-at-symbol.com, true",
                "'', true",
                "user@, false"
        })
         void testEmailValidation(String email, boolean shouldThrow){
            if (shouldThrow){
                assertThrows(IllegalArgumentException.class, () -> validator.validateEmail(email));
            } else {
                assertDoesNotThrow(() -> validator.validateEmail(email));
            }
        }
    }

    @Nested
    class PasswordValidationTests{

        @ParameterizedTest
        @CsvSource({
                "password1, false",
                "short1, true",
                "longpassword, true",
                "12345678, false"
        })

        void testPasswordValidation(String password, boolean shouldThrow){
            if (shouldThrow){
                assertThrows(IllegalArgumentException.class, () -> validator.validatePassword(password));
            } else {
                assertDoesNotThrow(() -> validator.validatePassword(password));
            }
        }
    }
}