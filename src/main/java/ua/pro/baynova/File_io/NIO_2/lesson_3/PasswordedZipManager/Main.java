package ua.pro.baynova.File_io.NIO_2.lesson_3.PasswordedZipManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String sourceFolder = "C:\\Users\\Dell\\IdeaProjects\\Java\\secret_files";
        String zipPath = "C:\\Users\\Dell\\IdeaProjects\\Java\\secret_archive.zip";
        String extractFolder = "C:\\Users\\Dell\\IdeaProjects\\Java\\extracted_secret";
        String password = "SuperSecret123!";

        createTestFiles(sourceFolder);
        logger.info("\n");

        PasswordedZipManager.createPasswordedArchive(sourceFolder, zipPath, password);
        PasswordedZipManager.showArchiveInfo(zipPath);

        logger.info("\n--- ПРОВЕРКА ПАРОЛЯ (без распаковки) ---\n");
        PasswordedZipManager.verifyPassword(zipPath, password);
        PasswordedZipManager.verifyPassword(zipPath, "WrongPassword");

        logger.info("\n");
        PasswordedZipManager.extractPasswordedArchive(zipPath, extractFolder, password);
    }

    private static void createTestFiles(String folderPath) {
        logger.info(">>> СОЗДАНИЕ ТЕСТОВЫХ ФАЙЛОВ <<<\n");

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
            logger.info("# Создана папка: {}\n", folderPath);
        }

        String[] fileNames = {"secret1.txt", "secret2.txt", "confidential.txt"};
        String[] fileContents = {
                "Это секретный файл 1\nСодержит конфиденциальную информацию",
                "Это секретный файл 2\nДанные: ID=12345, PASSWORD=encrypted",
                "КОНФИДЕНЦИАЛЬНО!\nЛичная информация сотрудников"
        };

        for (int i = 0; i < fileNames.length; i++) {
            File file = new File(folderPath + File.separator + fileNames[i]);

            try {
                if (!file.exists()) {
                    file.createNewFile();
                    FileWriter writer = new FileWriter(file);
                    writer.write(fileContents[i]);
                    writer.close();
                    logger.info("# Создан файл: {}", fileNames[i]);
                }
            } catch (IOException e) {
                logger.error("(!!!) Ошибка при создании файла: {}", fileNames[i]);
            }
        }

        logger.info("\n");
    }
}
