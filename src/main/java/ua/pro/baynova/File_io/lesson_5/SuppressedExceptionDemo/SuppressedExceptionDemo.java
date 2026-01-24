package ua.pro.baynova.File_io.lesson_5.SuppressedExceptionDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuppressedExceptionDemo {
    private static final Logger log = LoggerFactory.getLogger(SuppressedExceptionDemo.class);

    public static void main(String[] args) {
        log.info("Запуск SuppressedExceptionDemo ->>>");

        try (ProblematicResource resource = new ProblematicResource()) {
            resource.doWork();

        } catch (Exception e) {
            log.error("!! Основное исключение: {} ", e.toString());
            for (Throwable suppressed : e.getSuppressed()) {
                log.error("---Подавленное исключение: {}", suppressed.toString());
            }
        }

        log.info(">> Завершение программы <<");
    }
}