package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class OrderProducer implements Runnable {
    private final BlockingQueue<Order> queue;
    private final AtomicInteger orderIdCounter;
    private final Logger logger;

    public OrderProducer(BlockingQueue<Order> queue, AtomicInteger orderIdCounter, Logger logger) {
        this.queue = queue;
        this.orderIdCounter = orderIdCounter;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(1000);
                int id = orderIdCounter.getAndIncrement();
                Order order = new Order(id, "Client#" + id, id * 120);
                queue.put(order);
                logger.info("Создан заказ: " + order);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warning("Производство заказов остановлено");
        }
    }
}
