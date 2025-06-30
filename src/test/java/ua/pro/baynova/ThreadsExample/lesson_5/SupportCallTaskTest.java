package ua.pro.baynova.ThreadsExample.lesson_5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SupportCallTaskTest {

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
    void shouldPrintCorrectCallFlow() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        SupportCallTask task1 = new SupportCallTask("Анна", 1);
        SupportCallTask task2 = new SupportCallTask("Олег", 1);

        executor.submit(task1);
        executor.submit(task2);

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        String output = outContent.toString();

        assertTrue(output.contains("принял звонок от Анна"));
        assertTrue(output.contains("завершил звонок с Анна"));
        assertTrue(output.contains("принял звонок от Олег"));
        assertTrue(output.contains("завершил звонок с Олег"));
    }
}
