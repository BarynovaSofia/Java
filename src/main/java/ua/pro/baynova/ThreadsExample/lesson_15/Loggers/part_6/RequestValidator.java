package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_6;

import java.util.logging.Logger;

public class RequestValidator {
    private static final Logger logger = Logger.getLogger(RequestValidator.class.getName());

    public boolean isValid(Request request){
        if (request.getData() == null || request.getData().isBlank()){
            logger.warning("Неверная заявка (null или пустая): " + request);
            return false;
        }
        if (request.getId() % 5 == 0) {
            logger.warning("Подозрительная заявка: ID делится на 5 -> " + request);
            return false;
        }
        return true;
    }
}
