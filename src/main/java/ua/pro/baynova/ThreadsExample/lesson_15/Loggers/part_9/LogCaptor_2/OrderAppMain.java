package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_9.LogCaptor_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderAppMain {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        OrderService service = new OrderService();

        for (int i = 1; i <= 10; i++) {
            Order order = new Order(i, Math.random() * 2000 - 500, "Product" + i);

            executor.submit(() -> service.processOrder(order));
        }

        executor.shutdown();
    }
}
