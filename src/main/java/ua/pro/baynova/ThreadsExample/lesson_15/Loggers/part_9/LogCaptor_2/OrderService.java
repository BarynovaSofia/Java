package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_9.LogCaptor_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final PaymentProcessor paymentProcessor = new PaymentProcessor();

    public void processOrder(Order order) {
        logger.info("Начинаем обработку заказа: {}", order);

        if (order.amount() > 1000) {
            logger.warn("Сумма заказа {} подозрительно большая!", order.amount());
        }

        logger.debug("Передаём заказ в процессор оплаты...");
        try {
            paymentProcessor.processPayment(order);
            ShippingService.deliver(order);
            logger.info("Заказ успешно обработан: {}", order);
        } catch (Exception e) {
            logger.error("Ошибка при обработке заказа {}", order, e);
        }
    }
}
