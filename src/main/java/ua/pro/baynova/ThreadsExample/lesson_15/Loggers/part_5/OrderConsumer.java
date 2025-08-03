package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_5;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderConsumer implements Runnable {
    private final BlockingQueue<Order> queue;
    private final Logger logger;
    private final Random random = new Random();

    public OrderConsumer(BlockingQueue<Order> queue, Logger logger) {
        this.queue = queue;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Order order = queue.take();
                logger.info("Обрабатываю заказ: " + order);

                if (random.nextInt(10) < 2) {
                    throw new RuntimeException("Ошибка при обработке заказа: " + order);
                }

                Thread.sleep(1500);
                logger.info("Заказ успешно обработан: " + order);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warning("Обработка заказов остановлена");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Ошибка обработки: " + e.getMessage(), e);
        }
    }
}
