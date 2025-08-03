package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WarehouseAppMain {
    private static final Logger logger = Logger.getLogger(WarehouseAppMain.class.getName());

    public static void main(String[] args) throws Exception {
        LoggerSetup.setupLogger();

        OrderProcessor processor = new OrderProcessor();

        for (int i = 1; i <= 10; i++) {
            Order order = new Order(i, "Содержимое заказа " + i);
            try {
                processor.process(order);
            } catch (Exception e){
                logger.log(Level.SEVERE, "Ошибка при обработке заказа #" + order.getId(), e);
            }

        }
    }
}
