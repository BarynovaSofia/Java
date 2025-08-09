package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_7.MDC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

public class MdcWorker implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MdcWorker.class);
    private final int workerId;

    public MdcWorker(int workerId){
        this.workerId = workerId;
    }

    @Override
    public void run() {
        String requestId = "REQ-" + UUID.randomUUID();
        MDC.put("requestId", requestId);

        logger.info("Начало обработки задачи №{}", workerId);

        try {
            Thread.sleep((long) (1000 + Math.random() * 2000));
            logger.debug("Задача №{} обрабатывается..." , workerId);
        } catch (InterruptedException e) {
            logger.error("Ошибка в задаче №" + workerId, e);
            Thread.currentThread().interrupt();
        }

        logger.info("Завершение обработки задачи №{}", workerId);
        MDC.clear();
    }
}
