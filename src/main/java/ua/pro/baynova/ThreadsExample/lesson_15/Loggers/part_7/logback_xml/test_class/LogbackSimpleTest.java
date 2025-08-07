package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_7.logback_xml.test_class;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackSimpleTest {
    private static final Logger logger = LoggerFactory.getLogger(LogbackSimpleTest.class);

    public static void main(String[] args) {
        logger.info("Привет! Это INFO лог.");
        logger.debug("Это DEBUG лог, его не увидишь при уровне INFO.");
        logger.error("Вот это ERROR лог!");
    }
}
