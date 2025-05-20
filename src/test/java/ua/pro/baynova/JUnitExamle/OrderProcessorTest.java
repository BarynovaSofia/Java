package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderProcessorTest {
    OrderProcessor processor = new OrderProcessor();

    @Test
    void testSortOrdersPerformance(){
        List<String> orders = Arrays.asList(
                "Хлеб, Молоко, Яблоки, Чай, Сахар, Кофе, Макароны, Масло, Соль, Сыр"
        );

        assertTimeout(Duration.ofMillis(150), () -> {
            processor.sortOrders(orders);
        });
    }
}