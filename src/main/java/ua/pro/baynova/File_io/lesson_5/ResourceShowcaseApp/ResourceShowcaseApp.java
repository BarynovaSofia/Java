package ua.pro.baynova.File_io.lesson_5.ResourceShowcaseApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ResourceShowcaseApp {
    private static final Logger logger = LoggerFactory.getLogger(ResourceShowcaseApp.class);

    public static void main(String[] args) {
        logger.info("--- Запуск ResourceShowcaseApp ---");

        Scanner scanner = new Scanner(System.in);
        FileProcessor processor = new FileProcessor();

        System.out.print("Введите путь входного файла: ");
        String input = scanner.nextLine();

        System.out.print("Введите путь для выходного файла: ");
        String output = scanner.nextLine();

        processor.processFile(input, output);

        logger.info(">> Завершение ResourceShowcaseApp");
    }
}
