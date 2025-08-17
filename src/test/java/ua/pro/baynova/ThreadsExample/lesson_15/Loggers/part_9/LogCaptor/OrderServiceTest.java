package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_9.LogCaptor;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void shouldLogInfoWhenOrderCreated(){
        LogCaptor logCaptor = LogCaptor.forClass(OrderService.class);

        OrderService service = new OrderService();
        service.createOrder("123");

        assertTrue(logCaptor.getInfoLogs().contains("Order created: 123"));
    }

    @Test
    void shouldLogErrorWhenOrderIsEmpty(){
        LogCaptor logCaptor = LogCaptor.forClass(OrderService.class);

        OrderService service = new OrderService();
        service.createOrder("");

        assertTrue(logCaptor.getErrorLogs().contains("Order creation failed: empty"));
        assertEquals(1, logCaptor.getErrorLogs().size());
    }
}
