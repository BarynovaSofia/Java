package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_6;

import java.util.logging.Logger;

public class RequestHandler {
    private static final Logger logger = Logger.getLogger(RequestHandler.class.getName());

    public void handle(Request request){
        try {
            if (request.getId() % 9 == 0){
                throw new RuntimeException("Ошибка обработки заявки: ID делится на 9 -> " + request);
            }
            logger.info("Заявка обработана: " + request);
        } catch (Exception e){
            logger.severe("Сбой при обработке: " + e.getMessage());
        }
    }
}
