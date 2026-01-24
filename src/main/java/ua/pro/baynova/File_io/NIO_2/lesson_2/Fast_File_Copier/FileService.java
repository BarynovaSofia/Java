package ua.pro.baynova.File_io.NIO_2.lesson_2.Fast_File_Copier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private static final int BUFFER_SIZE = 1024 * 1024;

    public static void compareBufferPerformance(String sourcePath, String destDirectPath, String destNonDirectPath) {
        logger.info(">>> НАЧИНАЕТСЯ СРАВНЕНИЕ БУФЕРОВ <<<");

        logger.info("\n Копирование с Direct ByteBuffer ");
        long directTime = copyFileWithDirect(sourcePath, destDirectPath);

        logger.info("\n Копирование с Non-Direct ByteBuffer ");
        long nonDirectTime = copyFileWithNonDirect(sourcePath, destNonDirectPath);

        logger.info("\n>>> РЕЗУЛЬТАТЫ СРАВНЕНИЯ <<<");
        logger.info("Direct буфер:     {} мс", directTime);
        logger.info("Non-Direct буфер: {} мс", nonDirectTime);

        long difference = nonDirectTime - directTime;
        double percentageFaster = (difference * 100.0) / nonDirectTime;

        if (directTime < nonDirectTime) {
            logger.info("(*) Direct БЫСТРЕЕ на {} мс ({:.2f}%)", difference, percentageFaster);
        } else {
            logger.info("(!) Non-Direct БЫСТРЕЕ на {} мс", Math.abs(difference));
        }
    }

    private static long copyFileWithDirect(String sourcePath, String destinationPath) {
        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);

        if (!Files.exists(source)) {
            logger.error("(!!) Исходный файл не найден: {}", sourcePath);
            return 0;
        }

        long startTime = System.currentTimeMillis();

        try {
            FileInputStream inputStream = new FileInputStream(source.toFile());
            FileOutputStream outputStream = new FileOutputStream(destination.toFile());
            FileChannel inChannel = inputStream.getChannel();
            FileChannel outChannel = outputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
            logger.debug("Создан Direct ByteBuffer");

            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }

            inChannel.close();
            outChannel.close();
            inputStream.close();
            outputStream.close();

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            logger.info("~~ Direct копирование завершено: {} мс", duration);

            return duration;

        } catch (IOException e) {
            logger.error("(!) Ошибка при копировании с Direct буфером", e);
            return 0;
        }
    }

    private static long copyFileWithNonDirect(String sourcePath, String destinationPath) {
        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);

        if (!Files.exists(source)) {
            logger.error("(!) Исходный файл не найден: {}", sourcePath);
            return 0;
        }

        long startTime = System.currentTimeMillis();

        try {
            FileInputStream inputStream = new FileInputStream(source.toFile());
            FileOutputStream outputStream = new FileOutputStream(destination.toFile());
            FileChannel inChannel = inputStream.getChannel();
            FileChannel outChannel = outputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            logger.debug(">>Создан Non-Direct ByteBuffer");

            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }

            inChannel.close();
            outChannel.close();
            inputStream.close();
            outputStream.close();

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            logger.info("(*) Non-Direct копирование завершено: {} мс", duration);

            return duration;

        } catch (IOException e) {
            logger.error("(!!) Ошибка при копировании с Non-Direct буфером", e);
            return 0;
        }
    }

    public static void copyFileFast(String sourcePath, String destinationPath) {
        logger.info(">>Начинаем быстрое копирование файла>>");
        logger.debug("Источник: {}, Назначение: {}", sourcePath, destinationPath);

        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);

        if (!Files.exists(source)) {
            logger.error("(!) Исходный файл не найден: {}", sourcePath);
            return;
        }

        try {
            long startTime = System.currentTimeMillis();
            long fileSize = Files.size(source);
            logger.info("Размер файла: {} байт ({} МБ)", fileSize, fileSize / (1024 * 1024));

            FileInputStream inputStream = new FileInputStream(source.toFile());
            FileOutputStream outputStream = new FileOutputStream(destination.toFile());
            FileChannel inChannel = inputStream.getChannel();
            FileChannel outChannel = outputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
            logger.debug("(*) Создан Direct ByteBuffer размером: {} байт", BUFFER_SIZE);

            long copiedBytes = 0;
            int bytesRead;

            while ((bytesRead = inChannel.read(buffer)) > 0) {
                buffer.flip();
                outChannel.write(buffer);
                copiedBytes += bytesRead;

                buffer.clear();

                if (copiedBytes % (10 * 1024 * 1024) == 0) {
                    logger.info("Скопировано: {} МБ из {}", copiedBytes / (1024 * 1024), fileSize / (1024 * 1024));
                }
            }

            inChannel.close();
            outChannel.close();
            inputStream.close();
            outputStream.close();

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            logger.info("(*) Копирование завершено успешно!");
            logger.info("Всего скопировано: {} байт", copiedBytes);
            logger.info("Время выполнения: {} мс", duration);

        } catch (IOException e) {
            logger.error("(!!) Ошибка при копировании файла", e);
        }
    }
}
