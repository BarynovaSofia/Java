package ua.pro.baynova.ThreadsExample.lesson_5.Consolecapture;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderPrinterTest {

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;

    @BeforeEach
    void setUp(){
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown(){
        System.setOut(originalOut);
    }

    @Test
    void shouldPrintAllOrdersCorrectly(){
        OrderPrinter printer = new OrderPrinter();

        List<String> order = List.of("Капучино", "Сэндвич с тунцом", "Яблочный пирог");

        printer.printOrders(order);

        String output = outContent.toString();

        assertTrue(output.contains("1. Капучино"));
        assertTrue(output.contains("2. Сэндвич с тунцом"));
        assertTrue(output.contains("3. Яблочный пирог"));
        assertTrue(output.contains("====== Заказы клиентов ======"));
        assertTrue(output.contains("========= Конец списка ========="));
    }
}
