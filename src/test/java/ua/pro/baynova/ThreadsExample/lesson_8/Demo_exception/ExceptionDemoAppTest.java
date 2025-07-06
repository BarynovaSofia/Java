package ua.pro.baynova.ThreadsExample.lesson_8.Demo_exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionDemoAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp(){
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown(){
        System.setOut(originalOut);
    }

    @Test
    void main_shouldRunWithoutErrorsAndPrint(){
        ExceptionDemoApp.main(new String[]{});
        String output = outContent.toString();

        assertTrue(output.contains("Результат goodTask:") || output.contains("❗ ExecutionException:"));
    }
}
