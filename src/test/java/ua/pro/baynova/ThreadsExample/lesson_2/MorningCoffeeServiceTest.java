package ua.pro.baynova.ThreadsExample.lesson_2;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class MorningCoffeeServiceTest {

    @Test
    void shouldMakeCoffeeInCorrectOrder() throws InterruptedException{

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        MorningCoffeeService service = new MorningCoffeeService();
        service.makeCoffee();

        String result = out.toString();

        assertTrue(result.contains("Нагрев кофе-машины"));
        assertTrue(result.contains("Молим зёрна"));
        assertTrue(result.contains("Готовим кофе"));
        assertTrue(result.contains("✅ Кофе готов!"));
    }
}
