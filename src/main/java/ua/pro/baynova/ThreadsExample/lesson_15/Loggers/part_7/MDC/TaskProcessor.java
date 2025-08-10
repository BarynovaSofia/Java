package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_7.MDC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TaskProcessor implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TaskProcessor.class);
    private final String userId;

    public TaskProcessor (String userId){
        this.userId = userId;
    }

    @Override
    public void run() {
        String requestId = "REQ-" + UUID.randomUUID();

        MDC.put("requestId", requestId);
        MDC.put("userId", userId);

        try {
            logger.info("");
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            logger.info("");
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            logger.info("");
        } catch (InterruptedException e) {
            logger.error("Ошибка в задаче №", e);
            Thread.currentThread().interrupt();
        } finally {
            MDC.clear();
        }
    }
}
