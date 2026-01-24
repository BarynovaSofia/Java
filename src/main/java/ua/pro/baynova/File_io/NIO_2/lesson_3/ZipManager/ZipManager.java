package ua.pro.baynova.File_io.NIO_2.lesson_3.ZipManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipManager {
    private static final Logger logger = LoggerFactory.getLogger(ZipManager.class);
    private static final  int BUFFER_SIZE = 1024;

    public static void zipFolderWithArray(String folderPath, String zipPath) {
        logger.info("<< АРХИВАЦИЯ С МАССИВОМ >>");
        logger.info("(*) Исходная папка: {}", folderPath);
        logger.info("(*) Путь архива: {}\\n", zipPath);

        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            logger.error("(!!) Папка не найдена: {}", folderPath);
            return;
        }
        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            logger.warn("(>>)");
            return;
        }
        logger.info("(*) Найдено файлов: {}\n", files.length);

        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath));
            logger.debug(">> ZipOutputStream создан <<");

            for (File file : files) {
                if (file.isFile()) {
                    logger.info("() Добавление в архив: {}", file.getName());
                    addFileToZip(zos, file);
                }
            }

            zos.close();
            logger.info("\n (**) Архивация массивом завершена");
            logger.info(" (*) Архив создан: {}\n", zipPath);
        } catch (IOException e) {
            logger.error("(!!) Ошибка при архивации", e);
        }
    }

    public static void zipFolderWithList(String folderPath, String zipPath) {
        logger.info(" >>> АРХИВАЦИЯ СО СПИСКОМ (Stream API) <<< ");
        logger.info("(~) Исходная папка: {}",folderPath);
        logger.info("(>>) Путь архива: {}\n", zipPath);

        Path folder = Paths.get(folderPath);

        if (!Files.exists(folder)) {
            logger.error("(!!!) Папка не найдена: {}", folderPath);
            return;
        }
        try {
            List<Path> files = Files.list(folder)
                    .filter(Files::isRegularFile)
                    .toList();

            logger.info("(#) Найдено файлов: {}\n", files.size());
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath));
            logger.debug("(~~) ZipOutputStream создан");

            for (Path file : files) {
                logger.info("() Добавление в архив: {}", file.getFileName());
                addFileToZip(zos, file.toFile());
            }

            zos.close();
            logger.info(" (**) Архивация со списком завершена!");
            logger.info(" (*) Архив создан: {}\n", zipPath);

        } catch (IOException e) {
            logger.error("(!!!) Ошибка при архивации", e);
        }
    }

    public static void unzipFolder(String zipPath, String outputFolder) {
        logger.info(" <<< РАЗАРХИВАЦИЯ >>> ");
        logger.info("(**) Архив: {}", zipPath);
        logger.info("(>>) Папка назначения: {}\n", outputFolder);

        File outputDir = new File(outputFolder);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
            logger.info("(*) Создана папка: {}", outputFolder);
        }

        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath));
            logger.debug("(**) ZipInputStream создан");

            ZipEntry entry;
            int fileCount = 0;

            while ((entry = zis.getNextEntry()) != null) {
                logger.info("(>>) Распаковывание: {}", entry.getName());

                File newFile = new File( outputFolder + File.separator + entry.getName());
                new  File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int length;

                while ((length = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }

                fos.close();
                zis.closeEntry();
                fileCount++;
            }

            zis.close();
            logger.info("\n(~~) Разархивация завершена!");
            logger.info("(**) Распаковано файлов: {}\\n", fileCount);

        } catch (IOException e) {
            logger.error("(!!!) Ошибка при разархивации", e);
        }
    }

    private static void addFileToZip(ZipOutputStream zos, File file) throws IOException {
        ZipEntry entry = new ZipEntry(file.getName());
        zos.putNextEntry(entry);

        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;

        while ((length = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, length);
        }

        fis.close();
        zos.closeEntry();
        logger.debug("(~~) {} добавлен в архив ({} байт)", file.getName(), file.length());
    }
}
