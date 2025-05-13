package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoginValidatorTest {

    LoginValidator validator = new LoginValidator();

    @Nested
    @DisplayName("Тест на валидность логина")

    class LoginValidationTests{

        static List<String> validLogins(){
            return List.of(
                    "Anna12",
                    "Login123",
                    "User007",
                    "GoodLogin",
                    "Name1"
            );
        }

        static List<String> invalidLogins(){
            return java.util.Arrays.asList(
                    "",
                    "123Name",
                    "LongLoginName123",
                    "lo",
                    "Anna!",
                    null
            );
        }

        @ParameterizedTest
        @MethodSource("validLogins")
        @DisplayName("Проверка валидных логинов (не должно быть исключений)")

        void testValidLogins(String login){
            assertDoesNotThrow(() -> validator.validateLogin(login));
        }

        @ParameterizedTest
        @MethodSource("invalidLogins")
        @DisplayName("Проверка НЕвалидных логинов (должно выбрасываться исключение)")

        void testInvalidLogin(String login){
            assertThrows(IllegalArgumentException.class, () -> validator.validateLogin(login));
        }
    }



}