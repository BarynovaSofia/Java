package ua.pro.baynova.File_io.lesson_2.fileClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class FileInspectorApp {
    private static final Logger logger = LoggerFactory.getLogger(FileInspectorApp.class);

    public static void main(String[] args) {
        logger.info("=== Запуск FileInspectorApp ===");

        Scanner scanner = new Scanner(System.in);
        FileInspector inspector = new FileInspector();

        System.out.println("Введите путь к файлу или папке: ");
        String path = scanner.nextLine();
        inspector.checkFile(path);

        System.out.println("Создать новый файл (y/n)? ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Введите путь нового файла: ");
            inspector.createFile(scanner.nextLine());
        }

        System.out.println("Создать новую папку (y/n)? ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Введите путь новой папки: ");
            inspector.createDirectory(scanner.nextLine());
        }

        System.out.println("Удалить файл (y/n)? ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Введите путь к файлу для удаления: ");
            inspector.deleteFile(scanner.nextLine());
        }

        logger.info("Работа завершена.");
    }
}
