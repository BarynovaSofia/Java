package ua.pro.baynova.File_io.NIO_2.lesson_1.File_Backup_Tool;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("Текущая папка: " + System.getProperty("user.dir"));

        Path originalFile = Paths.get("C:\\Users\\Dell\\IdeaProjects\\Java\\document.txt.txt");
        Path backupFile = Paths.get("document_backup.txt");

        BackupService.createBackup(originalFile, backupFile);
        BackupService.showFileInfo(backupFile);
    }
}
