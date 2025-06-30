package ua.pro.baynova.ThreadsExample.lesson_5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class SupportServiceAppTest {
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
    void shouldRunMainAndPrintExpectedFlow(){
        SupportServiceApp.main(new String[]{});

        String output = outContent.toString();

        assertTrue(output.contains("Все звонки переданы в службу. Ждём завершения..."));
        assertTrue(output.contains("Служба поддержки завершила работу.")
                || output.contains("Некоторые звонки не завершились вовремя."),
                "Ожидали сообщение о завершении службы или о таймауте.");
        assertTrue(output.contains("принял звонок от"));
        assertTrue(output.contains("завершил звонок с"));
    }
}