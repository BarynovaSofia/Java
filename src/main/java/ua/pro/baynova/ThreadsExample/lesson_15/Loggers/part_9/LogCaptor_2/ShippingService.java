package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_9.LogCaptor_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShippingService {
    private static final Logger logger = LoggerFactory.getLogger("ShippingServiceLogger");

    public static void deliver(Order order) {
        logger.info("Доставка заказа {} начата", order.id());

        if (Math.random() < 0.1) {
            logger.error("Проблема с доставкой заказа {}", order.id(), new RuntimeException("Ошибка логистики"));
            throw new RuntimeException("Ошибка логистики");
        }

        logger.debug("Заказ {} передан курьеру", order.id());
        logger.info("Заказ {} доставлен успешно", order.id());
    }
}
