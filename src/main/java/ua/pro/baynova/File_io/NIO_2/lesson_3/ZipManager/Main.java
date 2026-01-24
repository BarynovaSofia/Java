package ua.pro.baynova.File_io.NIO_2.lesson_3.ZipManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String sourceFolder = "C:\\Users\\Dell\\IdeaProjects\\Java\\test_folder";
        String zipWithArray = "C:\\Users\\Dell\\IdeaProjects\\Java\\archive_array.zip";
        String zipWithList = "C:\\Users\\Dell\\IdeaProjects\\Java\\archive_list.zip";
        String unzipFolder = "C:\\Users\\Dell\\IdeaProjects\\Java\\extracted_files";

        createTestFiles(sourceFolder);
        logger.info("\n");

        ZipManager.zipFolderWithArray(sourceFolder, zipWithArray);
        ZipManager.zipFolderWithList(sourceFolder, zipWithList);
        ZipManager.unzipFolder(zipWithArray, unzipFolder);
    }

    private static void createTestFiles(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
            logger.info("(>>) Создана папка: {}\n", folderPath);
        }

        String[] fileNames = {"document.txt", "notes.txt", "info.txt", "data.txt"};
        String[] fileContents = {
                "Это важный документ!\nСодержит критическую информацию.",
                "Мои заметки:\n- Учить Java\n- Понять ZIP архивы\n- Практиковаться",
                "Информация о проекте:\nЭто учебный проект для изучения File I/O",
                "Данные:\nФайл 1: 100\nФайл 2: 200\nФайл 3: 300"
        };

        for (int i = 0; i < fileNames.length; i++) {
            File file = new File(folderPath + File.separator + fileNames[i]);

            try {
                if (!file.exists()) {
                    file.createNewFile();
                    FileWriter writer = new FileWriter(file);
                    writer.write(fileContents[i]);
                    writer.close();
                    logger.info("(>>) Создан файл: {}", fileNames[i]);
                }
            } catch (IOException e) {
                logger.error("(!!!) Ошибка при создании файла: {}", fileNames[i]);
            }
        }
        logger.info("\n");
    }
}
