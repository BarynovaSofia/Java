package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_5;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class OrderProcessingApp {
    public void start() throws IOException {
        Logger logger = LoggerSetup.setup();
        BlockingQueue<Order> queue = new LinkedBlockingQueue<>();
        AtomicInteger counter = new AtomicInteger(1);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> producerFuture = executor.submit(new OrderProducer(queue, counter, logger));
        Future<?> consumerFuture = executor.submit(new OrderConsumer(queue, logger));

        logger.info("OrderProcessingApp запущено");

        ScheduledExecutorService terminator = Executors.newSingleThreadScheduledExecutor();
        terminator.schedule(() -> {
            logger.info("Время вышло. Останавливаем приложение...");
            executor.shutdownNow();
        }, 10, TimeUnit.SECONDS);

        try {
            executor.awaitTermination(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.warning("Ожидание завершения было прервано");
            Thread.currentThread().interrupt();
        } finally {
            terminator.shutdownNow();
            logger.info("Приложение завершено");
        }
    }
}

