package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_9.LogCaptor_2;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LogCaptorBasicTest {

    private LogCaptor orderServiceCaptor;
    private LogCaptor paymentCaptor;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderServiceCaptor = LogCaptor.forClass(OrderService.class);
        paymentCaptor = LogCaptor.forClass(PaymentProcessor.class);
        orderService = new OrderService();
    }

    @AfterEach
    void tearDown() {
        orderServiceCaptor.close();
        paymentCaptor.close();
    }

    @Test
    @DisplayName("Подсчет логов по уровням")
    void testLogCounting() {
        Order normalOrder = new Order(1, 500.0, "Phone");
        Order largeOrder = new Order(2, 1500.0, "Laptop");

        try {
            orderService.processOrder(normalOrder);
            orderService.processOrder(largeOrder);
        } catch (Exception e) {
        }

        System.out.println("=== ПОДСЧЕТ ЛОГОВ ===");
        System.out.println("INFO логов: " + orderServiceCaptor.getInfoLogs().size());
        System.out.println("DEBUG логов: " + orderServiceCaptor.getDebugLogs().size());
        System.out.println("WARN логов: " + orderServiceCaptor.getWarnLogs().size());
        System.out.println("ERROR логов: " + orderServiceCaptor.getErrorLogs().size());

        assertTrue(orderServiceCaptor.getInfoLogs().size() >= 2);
        assertEquals(1, orderServiceCaptor.getWarnLogs().size());
    }

    @Test
    @DisplayName("Фильтрация WARN логов")
    void testWarnLogFiltering() {
        Order bigOrder = new Order(1, 1200.0, "MacBook");

        try {
            orderService.processOrder(bigOrder);
        } catch (Exception e) {
        }

        List<String> warnLogs = orderServiceCaptor.getWarnLogs();

        System.out.println("=== WARN ЛОГИ ===");
        warnLogs.forEach(System.out::println);

        assertEquals(1, warnLogs.size());
        assertTrue(warnLogs.get(0).contains("подозрительно большая"));
        assertTrue(warnLogs.get(0).contains("1200.0"));
    }

    @Test
    @DisplayName("Анализ ERROR логов с исключениями")
    void testErrorLogsWithExceptions() {
        Order invalidOrder = new Order(1, -100.0, "Invalid");

        assertThrows(IllegalArgumentException.class, () -> {
            PaymentProcessor processor = new PaymentProcessor();
            processor.processPayment(invalidOrder);
        });

        List<String> errorLogs = paymentCaptor.getErrorLogs();

        System.out.println("=== ERROR ЛОГИ ===");
        errorLogs.forEach(System.out::println);

        assertTrue(errorLogs.size() >= 1);
        assertTrue(errorLogs.get(0).contains("Некорректная сумма"));
        assertTrue(errorLogs.get(0).contains("-100.0"));
    }

    @Test
    @DisplayName("Поиск логов по содержимому")
    void testLogContentSearch() {
        Order testOrder = new Order(42, 800.0, "SearchTest");

        try {
            orderService.processOrder(testOrder);
        } catch (Exception e) {
        }

        List<String> allLogs = orderServiceCaptor.getLogs();

        List<String> orderIdLogs = allLogs.stream()
                .filter(log -> log.contains("42"))
                .toList();

        List<String> startLogs = allLogs.stream()
                .filter(log -> log.contains("Начинаем обработку"))
                .toList();

        System.out.println("=== ПОИСК В ЛОГАХ ===");
        System.out.println("Логи с ID 42: " + orderIdLogs.size());
        System.out.println("Логи о начале: " + startLogs.size());

        assertTrue(orderIdLogs.size() > 0);
        assertTrue(startLogs.size() > 0);
    }

    @Test
    @DisplayName("Сравнение логов разных сервисов")
    void testMultiServiceLogs() {
        Order order = new Order(1, 300.0, "MultiService");

        try {
            orderService.processOrder(order);
        } catch (Exception e) {
        }

        int orderServiceLogs = orderServiceCaptor.getLogs().size();
        int paymentServiceLogs = paymentCaptor.getLogs().size();

        System.out.println("=== СРАВНЕНИЕ СЕРВИСОВ ===");
        System.out.println("OrderService логов: " + orderServiceLogs);
        System.out.println("PaymentProcessor логов: " + paymentServiceLogs);

        assertTrue(orderServiceLogs > 0);
    }
}