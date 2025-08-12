package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_7.Log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.Random;

public class RequestProcessor {
    private static final Logger logger = LogManager.getLogger(RequestProcessor.class);
    private final Random random = new Random();

    public void processRequest(String requestId, String userId) {
        try {
            ThreadContext.put("requestId", requestId);
            ThreadContext.put("userId", userId);

            logger.info("Начало обработки запроса");

            Thread.sleep(random.nextInt(1500) + 500);

            if (random.nextBoolean()) {
                logger.warn("Обнаружено подозрительное поведение");
            }

            Thread.sleep(random.nextInt(1500) + 500);

            logger.info("Обработка запроса завершена");
        } catch (InterruptedException e) {
            logger.error("Ошибка в процессе обработки", e);
        } finally {
            ThreadContext.clearAll();
        }
    }
}
