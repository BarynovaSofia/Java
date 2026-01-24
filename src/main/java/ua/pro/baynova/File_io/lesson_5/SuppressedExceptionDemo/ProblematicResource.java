package ua.pro.baynova.File_io.lesson_5.SuppressedExceptionDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProblematicResource implements AutoCloseable {
    private static final Logger logger = LoggerFactory.getLogger(ProblematicResource.class);

    public ProblematicResource() {
        logger.info("---Создан ProblematicResource---");
    }

    public void doWork() {
        logger.info(">> Выполняется работа...");
        throw new RuntimeException("!! Ошибка во время работы ресурса ");
    }

    @Override
    public void close() {
        logger.warn(">> Закрытие ресурса...");
        throw new IllegalArgumentException("!! Ошибка при закрытии ресурса ");
    }
}
