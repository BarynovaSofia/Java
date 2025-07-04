package ua.pro.baynova.ThreadsExample.lesson_7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostureReminderAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void redirectOutput(){
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreOutput(){
        System.setOut(originalOut);
    }

    @Test
    void main_shouldPrintStartupMessageImmediately(){
        PostureReminderApp.main(new String[]{});

        String output = outContent.toString();

        assertTrue(output.contains("Запускаю приложение-напоминалку"),
                "Ожидали, что main() выведет стартовое сообщение");
    }
}
