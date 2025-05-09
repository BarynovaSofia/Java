package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService service = new UserService();

    @Test
    void testGreetWithName(){
        String result = service.greetUser("Аня");
        assertEquals("Привет, Аня!!!", result);
    }

    @Test
    void testGreetWithNull(){
        String result = service.greetUser(null);
        assertEquals("Привет, гость!!!", result);
    }

    @Test
    void testGreetWithSpaces(){
        String result = service.greetUser("  ");
        assertEquals("Привет, гость!!!", result);
    }

    @Test
    void testGreetWithEmptyString(){
        String result = service.greetUser("");
        assertEquals("Привет, гость!!!", result);
    }

    @Test
    void testNameWithNumbersThrowsException(){
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            service.greetUser("121132Аня!!!");
        });
        assertEquals("Имя не должно содержать цифры!!!", ex.getMessage());
    }

    @Test
    void testNameWithLatinLetters(){
        Exception ex = assertThrows(IllegalArgumentException.class, () ->{
            service.greetUser("Anya");
        });
        assertEquals("Имя должно быть на кириллице", ex.getMessage());
    }

    @Test
    void testNameMixedLatinAndCyrillic(){
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            service.greetUser("Aня");
        });
        assertEquals("Имя должно быть на кириллице", ex.getMessage());
    }
}