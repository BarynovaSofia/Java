package ua.pro.baynova.File_io.lesson_2.fileClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileInspector {

    private static final Logger logger = LoggerFactory.getLogger(FileInspector.class);

    public void checkFile(String path) {
        File file = new File(path);

        logger.info("Проверка файла или папки: {}", path);
        logger.info("Абсолютный путь: {}", file.getAbsolutePath());
        logger.info("Имя: {}", file.getName());
        logger.info("Существует: {}", file.exists());
        logger.info("Это файл: {}", file.isFile());
        logger.info("Это папка: {}", file.isDirectory());
        logger.info("Размер: {} байт", file.length());
    }

    public void createFile(String path) {
        File file = new File(path);
        try {
            if (file.createNewFile()) {
                logger.info("Файл создан: {}", file.getAbsolutePath());
            } else {
                logger.warn("Файл уже существует: {}", file.getAbsolutePath());
            }
        } catch (IOException e) {
            logger.error("Ошибка при создании файла: {}", e.getMessage(), e);
        }
    }

    public void deleteFile(String path) {
        File file = new File(path);
        if (file.delete()) {
            logger.info("Файл удалён: {}", file.getAbsolutePath());
        } else {
            logger.warn("Не удалось удалить файл (возможно, не существует): {}", path);
        }
    }

    public void createDirectory(String path) {
        File dir = new File(path);
        if (dir.mkdirs()) {
            logger.info("Папка создана: {}", dir.getAbsolutePath());
        } else {
            logger.warn("Папка уже существует или не удалось создать: {}", path);
        }
    }
}
