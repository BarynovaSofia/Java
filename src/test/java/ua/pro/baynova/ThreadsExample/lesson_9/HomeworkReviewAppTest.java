package ua.pro.baynova.ThreadsExample.lesson_9;

import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HomeworkReviewAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void runHomeworkCheck_shouldCompleteWithinTimeOrInterrupt() {
        HomeworkReviewApp.runHomeworkCheck();

        String output = outContent.toString();

        assertTrue(output.contains("📘 Даём 6 секунд на проверку заданий"));

        assertTrue(output.contains("✅ Все проверки завершены вовремя!") ||
                output.contains("❗Некоторые проверки не завершены вовремя. Прерываем всё."));

        assertTrue(output.contains("🔵 Сессия завершена."));
    }
}
