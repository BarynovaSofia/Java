package ua.pro.baynova.File_io.NIO_2.lesson_3.NestedZipManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class NestedZipManager {
    private static final Logger logger = LoggerFactory.getLogger(NestedZipManager.class);
    private static final int BUFFER_SIZE = 1024;

    public static void zipFolderRecursive(String folderPath, String zipPath) {
        logger.info(">>> АРХИВАЦИЯ С ВЛОЖЕННЫМИ ПАПКАМИ <<<\n");
        logger.info("# Исходная папка: {}", folderPath);
        logger.info(">> Путь архива: {}\n", zipPath);

        File sourceFolder = new File(folderPath);

        if (!sourceFolder.exists() || !sourceFolder.isDirectory()) {
            logger.error("(!!!) Папка не найдена: {}", folderPath);
            return;
        }

        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath));
            logger.debug("(*) ZipOutputStream создан\n");

            addFolderToZip(zos, sourceFolder, "");

            zos.close();
            logger.info("# Архивация завершена!");
            logger.info("# Архив создан: {}\n", zipPath);

        } catch (IOException e) {
            logger.error("(!!!) Ошибка при архивации", e);
        }
    }

    private static void addFolderToZip(ZipOutputStream zos, File folder, String parentPath) throws IOException {
        File[] files = folder.listFiles();

        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                String entryPath = parentPath.isEmpty() ? file.getName() : parentPath + file.getName();
                logger.info("-> Добавлен файл: {}", entryPath);

                ZipEntry entry = new ZipEntry(entryPath);
                zos.putNextEntry(entry);

                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[BUFFER_SIZE];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                fis.close();
                zos.closeEntry();

                logger.debug("# Файл добавлен ({} байт)", file.length());

            } else if (file.isDirectory()) {
                String folderName = parentPath + file.getName() + "/";
                logger.info("(*) Вход в папку: {}", folderName);

                addFolderToZip(zos, file, folderName);
            }
        }
    }

    public static void unzipFolderRecursive(String zipPath, String outputFolder) {
        logger.info("<<<< РАЗАРХИВАЦИЯ СО СТРУКТУРОЙ >>>>\n");
        logger.info("(*) Архив: {}", zipPath);
        logger.info("# Папка назначения: {}\n", outputFolder);

        File outputDir = new File(outputFolder);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
            logger.info(">> Создана папка: {}\n", outputFolder);
        }

        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath));
            logger.debug("# ZipInputStream создан\n");

            ZipEntry entry;
            int fileCount = 0;

            while ((entry = zis.getNextEntry()) != null) {
                logger.info("-> Распаковка: {}", entry.getName());

                File newFile = new File(outputFolder + File.separator + entry.getName());

                File parentDir = newFile.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                    logger.debug(">> Создана папка: {}", parentDir.getPath());
                }

                FileOutputStream fos = new FileOutputStream(newFile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int length;

                while ((length = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }

                fos.close();
                zis.closeEntry();
                logger.debug(">> Файл распакован");
                fileCount++;
            }

            zis.close();
            logger.info("\n(*) Разархивация завершена!");
            logger.info("# Распаковано файлов: {}\n", fileCount);

        } catch (IOException e) {
            logger.error("(!!!) Ошибка при разархивации", e);
        }
    }

    public static void showArchiveStructure(String zipPath) {
        logger.info("---> СТРУКТУРА АРХИВА <---\n");
        logger.info("# Архив: {}\n", zipPath);

        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath));
            ZipEntry entry;
            int level;

            logger.info("Содержимое:");
            while ((entry = zis.getNextEntry()) != null) {
                level = entry.getName().split("/").length - 1;

                String indent = "  ".repeat(level);
                String name = entry.getName().substring(entry.getName().lastIndexOf("/") + 1);

                if (entry.getName().endsWith("/")) {
                    logger.info("{}(*) {}", indent, name);
                } else {
                    logger.info("{}# {} ({} байт)", indent, name, entry.getSize());
                }

                zis.closeEntry();
            }

            zis.close();
            logger.info("\n");

        } catch (IOException e) {
            logger.error("(!!!) Ошибка при чтении архива", e);
        }
    }
}
