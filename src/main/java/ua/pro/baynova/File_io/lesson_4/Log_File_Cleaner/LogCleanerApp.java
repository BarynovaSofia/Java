package ua.pro.baynova.File_io.lesson_4.Log_File_Cleaner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class LogCleanerApp {

    private static final Logger logger = LoggerFactory.getLogger(LogCleanerApp.class);

    public static void main(String[] args) {
        logger.info("=== Запуск LogCleanerApp ===");

        Scanner scanner = new Scanner(System.in);
        LogGenerator generator = new LogGenerator();
        LogCleaner cleaner = new LogCleaner();

        System.out.print("Введите путь для генерации исходного лога: ");
        String source = scanner.nextLine();

        System.out.print("Введите путь для файла отчёта: ");
        String report = scanner.nextLine();

        System.out.print("Сколько строк сгенерировать в логе? ");
        int linesCount = Integer.parseInt(scanner.nextLine());

        generator.generatorLog(source, linesCount);
        cleaner.filterErrors(source, report);

        logger.info("=== Работа завершена ===");
    }
}
