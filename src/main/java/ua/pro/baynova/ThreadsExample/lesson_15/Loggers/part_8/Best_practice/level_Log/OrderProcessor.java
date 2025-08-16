package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_8.Best_practice.level_Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Random;

public class OrderProcessor implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);
    private final Order order;
    private final Random random = new Random();

    public OrderProcessor(Order order){
        this.order = order;
    }

    @Override
    public void run() {
        MDC.put("orderId", order.getId());

        try {
            logger.info("Начинаем обработку заказа");
            logger.debug("Заказ {} содержит {} товаров", order.getId(), order.getItemCount());

            if (order.getItemCount() > 10){
                logger.warn("Заказ {} слишком большой: {} товаров", order.getId(), order.getItemCount());
            }

            if (random.nextInt(5) == 0){
                throw new RuntimeException("Ошибка оплаты");
            }

            Thread.sleep(500);
            logger.info("Заказ {} успешно обработан", order.getId());

        } catch (Exception e){
            logger.error("Не удалось обработать заказ {}", order.getId(), e);
        } finally {
            MDC.clear();
        }
    }
}
