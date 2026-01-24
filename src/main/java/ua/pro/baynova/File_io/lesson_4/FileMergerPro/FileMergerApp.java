package ua.pro.baynova.File_io.lesson_4.FileMergerPro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class FileMergerApp {

    private static final Logger logger = LoggerFactory.getLogger(FileMergerApp.class);

    public static void main(String[] args) {
        logger.info("=== Запуск FileMergerApp ===");

        Scanner scanner = new Scanner(System.in);
        FileMerger merger = new FileMerger();

        System.out.print("Введите путь к первому файлу: ");
        String first = scanner.nextLine();

        System.out.print("Введите путь ко второму файлу: ");
        String second = scanner.nextLine();

        System.out.print("Введите путь для итогового файла: ");
        String output = scanner.nextLine();

        merger.mergeFiles(first, second, output);

        logger.info("=== Завершение работы FileMergerApp ===");
    }
}
