package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_8.Best_practice.level_Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final ExecutorService executor;

    public OrderService(int threads){
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public void startProcessing(int orderCount){
        logger.info("Запуск обработки {} заказов", orderCount);

        for (int i = 1; i <= orderCount; i++) {
            Order order = new Order("ORD-" + i, (int)(Math.random() * 15));
            logger.debug("Создан заказ {}", order.getId());
            executor.submit(new OrderProcessor(order));
        }
        executor.shutdown();
        logger.info("Все заказы отправлены на обработку");
    }
}
