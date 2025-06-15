package ua.pro.baynova.TDD_Example.lesson_4;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderHistoryTest {

    @Test
    void testAddAndGetLastOrders(){
        OrderHistory history = new OrderHistory();

        history.addOrder("Laptop");
        history.addOrder("Phone");
        history.addOrder("Book");

        List<String> lastTwo = history.getLastOrders(2);

        assertEquals(List.of("Book", "Phone"), lastTwo);
    }

    @Test
    void testClearHistory(){
        OrderHistory history = new OrderHistory();

        history.addOrder("Book");
        history.addOrder("Phone");
        history.clear();

        assertTrue(history.isEmpty());
    }

    @Test
    void testIsEmptyInitially(){
        OrderHistory history = new OrderHistory();

        assertTrue(history.isEmpty());
    }

    @Test
    void testGetZeroOrders(){
        OrderHistory history = new OrderHistory();

        history.addOrder("Laptop");
        history.addOrder("Phone");

        List<String> result = history.getLastOrders(0);

        assertEquals(List.of(), result);
    }

    @Test
    void testGetMoreThanAvailableOrders(){
        OrderHistory history = new OrderHistory();

        history.addOrder("Book");

        List<String> result = history.getLastOrders(5);

        assertEquals(List.of("Book"), result);
    }

    @Test
    void testAddNullOrderThrowsException(){
        OrderHistory history = new OrderHistory();

        assertThrows(IllegalArgumentException.class, () -> history.addOrder(null));
    }
}
