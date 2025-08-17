package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_9.LogCaptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public void createOrder(String orderId){
        if (orderId == null || orderId.isEmpty()){
            logger.error("Order creation failed: empty");
            return;
        }
        logger.info("Order created: {}", orderId);
    }
}
