package ua.pro.baynova.File_io.NIO_2.lesson_3.NestedZipManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String sourceFolder = "C:\\Users\\Dell\\IdeaProjects\\Java\\test_nested_structure";
        String zipPath = "C:\\Users\\Dell\\IdeaProjects\\Java\\nested_archive.zip";
        String unzipFolder = "C:\\Users\\Dell\\IdeaProjects\\Java\\extracted_nested";

        createNestedStructure(sourceFolder);
        logger.info("\n");

        NestedZipManager.zipFolderRecursive(sourceFolder, zipPath);
        NestedZipManager.showArchiveStructure(zipPath);
        NestedZipManager.unzipFolderRecursive(zipPath, unzipFolder);
    }

    private static void createNestedStructure(String basePath) {
        logger.info("-> СОЗДАНИЕ ТЕСТОВОЙ СТРУКТУРЫ ->\n");

        String[][] folders = {
                {"documents"},
                {"documents", "reports"},
                {"images"}
        };

        String[][] files = {
                {"readme.txt", "root", "Это главный файл проекта"},
                {"documents", "file1.txt", "Документ 1\nСодержит важную информацию"},
                {"documents/reports", "report1.txt", "Отчёт 1\nДанные за январь"},
                {"documents/reports", "report2.txt", "Отчёт 2\nДанные за февраль"},
                {"images", "photo.jpg", "Это метаинформация о фото"}
        };

        try {
            for (String[] folder : folders) {
                StringBuilder path = new StringBuilder(basePath);
                for (String part : folder) {
                    path.append(File.separator).append(part);
                }

                File dir = new File(path.toString());
                if (!dir.exists()) {
                    dir.mkdirs();
                    logger.info("# Создана папка: {}", String.join("/", folder));
                }
            }

            for (String[] fileInfo : files) {
                StringBuilder path = new StringBuilder(basePath);

                if (fileInfo[1].equals("root")) {
                    path.append(File.separator).append(fileInfo[0]);
                } else {
                    path.append(File.separator).append(fileInfo[0]);
                    path.append(File.separator).append(fileInfo[1]);
                }

                File file = new File(path.toString());
                if (!file.exists()) {
                    file.createNewFile();
                    FileWriter writer = new FileWriter(file);
                    writer.write(fileInfo[2]);
                    writer.close();
                    logger.info("# Создан файл: {}",
                            fileInfo[1].equals("root") ? fileInfo[0] : fileInfo[0] + "/" + fileInfo[1]);
                }
            }

            logger.info("\n(#) Структура папок создана!\n");

        } catch (IOException e) {
            logger.error("(!!!) Ошибка при создании структуры", e);
        }
    }
}
