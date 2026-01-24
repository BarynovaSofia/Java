package ua.pro.baynova.File_io.NIO_2.lesson_1.File_Backup_Tool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BackupService {

    public static void createBackup(Path source, Path backup) {
        try {
            if (!Files.exists(source)) {
                System.out.println("!! Файл не найден: " + source);
                return;
            }

            Files.copy(source, backup, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("~~Резервная копия создана: " + backup);
        } catch (IOException e) {
            System.out.println("<!> Ошибка при копировании: " + e.getMessage());
        }
    }

    public static void deleteBackup(Path backup) {
        try {
            if (!Files.exists(backup)) {
                System.out.println("!! Бэкап не найден: " + backup);
                return;
            }
            Files.delete(backup);
            System.out.println("~~Бэкап удалён: " + backup);
        } catch (IOException e) {
            System.out.println("<!> Ошибка при удалении: " + e.getMessage());
        }
    }

    public static void moveBackup(Path source, Path destination) {
        try {
            if (!Files.exists(source)) {
                System.out.println("<!> Файл не найден: " + source);
                return;
            }

            Path destFolder = destination.getParent();
            if (!Files.exists(destFolder)) {
                Files.createDirectories(destFolder);
                System.out.println("~~ Создана папка: " + destFolder);
            }

            Files.move(source, destination);
            System.out.println("~~ Файл перемещён в: " + destination);

        } catch (IOException e) {
            System.out.println("!!! Ошибка при перемещении: " + e.getMessage());
        }
    }

    public static void showFileInfo(Path files) {
        try {
            if (Files.exists(files)) {
                System.out.println("\n>> Информация о файле: " + files.getFileName());
                System.out.println(" Размер: " + Files.size(files) + " байт");
                System.out.println(" Это файл: " + Files.isRegularFile(files));
            } else {
                System.out.println("!!! Файл не существует: " + files);
            }
        } catch (IOException e) {
            System.out.println("<!> Ошибка при чтении информации: " + e.getMessage());
        }
    }
}
