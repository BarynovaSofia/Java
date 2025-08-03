package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_5;

import ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_4.LoggerSetup;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

public class OrderProcessor {
    private final Logger logger = Logger.getLogger(OrderProcessor.class.getName());
    private final Random random = new Random();

    public void process(Order order) throws Exception{
        logger.info("Обрабатываем заказ # " + order.getId());

        int chance = random.nextInt(10);

        switch (chance){
            case 0 -> throw new IllegalStateException("Заказ повреждён!");
            case 1 -> throw new NullPointerException("Коробка не найдена!");
            case 2 -> throw new IOException("База недоступна!");
            default -> logger.info("Заказ #" + order.getId() + " успешно упакован");
        }
    }
}
