package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_9.LogCaptor_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentProcessor {
    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessor.class);

    public void processPayment(Order order) {
        if (order.amount() < 0) {
            logger.error("Некорректная сумма в заказе: {}", order.amount(), new IllegalArgumentException("Отрицательная сумма"));
            throw new IllegalArgumentException("Сумма заказа не может быть отрицательной");
        }

        if (Math.random() < 0.2) {
            RuntimeException ex = new RuntimeException("Сбой платёжной системы");
            logger.error("Ошибка при оплате заказа {}: {}", order.id(), ex.getMessage(), ex);
            throw ex;
        }

        logger.info("Оплата заказа {} прошла успешно", order.id());
    }
}
