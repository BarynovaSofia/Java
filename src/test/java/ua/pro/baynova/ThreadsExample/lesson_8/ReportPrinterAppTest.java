package ua.pro.baynova.ThreadsExample.lesson_8;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ReportPrinterAppTest {

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
        ReportPrinterApp.main(new String[]{});
        String output = outContent.toString();

        assertTrue(output.contains("🖨️ execute(): Печатаю отчёт...") ||
                output.contains("🖨️ submit(): Печатаю отчёт...") ||
                output.contains("❗ Поймали ошибку из submit()"));
    }

}