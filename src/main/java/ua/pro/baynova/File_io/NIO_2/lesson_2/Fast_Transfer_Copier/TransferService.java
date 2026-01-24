package ua.pro.baynova.File_io.NIO_2.lesson_2.Fast_Transfer_Copier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TransferService {
    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);
    private static final int BUFFER_SIZE = 1024 * 1024;

    public static void compareAllMethods(String sourcePath, String dest1, String dest2, String dest3) {
        logger.info("<<<< СРАВНЕНИЕ ВСЕХ СПОСОБОВ КОПИРОВАНИЯ >>>>\n");

        Path source = Paths.get(sourcePath);

        if (!Files.exists(source)) {
            logger.error("(!) Исходный файл не найден: {}", sourcePath);
            return;
        }

        try {
            long fileSize = Files.size(source);
            logger.info("(*) Размер файла: {} МБ\n", fileSize / (1024 * 1024));

            logger.info("--- СПОСОБ 1: ByteBuffer (Direct) ---");
            logger.info("Описание: Копирование через Direct ByteBuffer с циклом");
            long time1 = copyWithDirectBuffer(sourcePath, dest1);

            logger.info("\n>> СПОСОБ 2: ByteBuffer (Non-Direct) <<");
            logger.info("Описание: Копирование через Non-Direct ByteBuffer с циклом");
            long time2 = copyWithNonDirectBuffer(sourcePath, dest2);

            logger.info("\n>> СПОСОБ 3: transferTo() <<");
            logger.info("Описание: Прямое копирование между каналами, ОС делает всё");
            long time3 = copyWithTransferTo(sourcePath, dest3);

            printComparison(time1, time2, time3);

        } catch (IOException e) {
            logger.error("(!) Ошибка при сравнении", e);
        }
    }

    private static long copyWithDirectBuffer(String sourcePath, String destinationPath) {
        long startTime = System.currentTimeMillis();

        try {
            FileChannel inChannel = FileChannel.open(
                    Paths.get(sourcePath),
                    StandardOpenOption.READ
            );

            FileChannel outChannel = FileChannel.open(
                    Paths.get(destinationPath),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

            ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);

            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }

            inChannel.close();
            outChannel.close();

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            logger.info("(~) Завершено за: {} мс\n", duration);

            return duration;

        } catch (IOException e) {
            logger.error("(!) Ошибка", e);
            return 0;
        }
    }

    private static long copyWithNonDirectBuffer(String sourcePath, String destinationPath) {
        long startTime = System.currentTimeMillis();

        try {
            FileChannel inChannel = FileChannel.open(
                    Paths.get(sourcePath),
                    StandardOpenOption.READ
            );

            FileChannel outChannel = FileChannel.open(
                    Paths.get(destinationPath),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }

            inChannel.close();
            outChannel.close();

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            logger.info("(~) Завершено за: {} мс\n", duration);

            return duration;

        } catch (IOException e) {
            logger.error("(!) Ошибка", e);
            return 0;
        }
    }

    private static long copyWithTransferTo(String sourcePath, String destinationPath) {
        long startTime = System.currentTimeMillis();

        try {
            FileChannel inChannel = FileChannel.open(
                    Paths.get(sourcePath),
                    StandardOpenOption.READ
            );

            FileChannel outChannel = FileChannel.open(
                    Paths.get(destinationPath),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

            long fileSize = inChannel.size();

            inChannel.transferTo(0, fileSize, outChannel);

            inChannel.close();
            outChannel.close();

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            logger.info("(~) Завершено за: {} мс\n", duration);

            return duration;

        } catch (IOException e) {
            logger.error("(!) Ошибка", e);
            return 0;
        }
    }

    private static void printComparison(long time1, long time2, long time3) {
        logger.info("----- ИТОГОВОЕ СРАВНЕНИЕ -----");
        logger.info("1 Direct Buffer:      {} мс", time1);
        logger.info("2️ Non-Direct Buffer:  {} мс", time2);
        logger.info("3️ transferTo():       {} мс", time3);
        logger.info("");

        long fastestTime = Math.min(time3, Math.min(time1, time2));

        if (time3 == fastestTime && time3 > 0) {
            double percentFasterThan1 = ((time1 - time3) * 100.0) / time1;
            double percentFasterThan2 = ((time2 - time3) * 100.0) / time2;
            logger.info(" transferTo() САМЫЙ БЫСТРЫЙ!");
            logger.info("   На {:.1f}% быстрее чем Direct Buffer", percentFasterThan1);
            logger.info("   На {:.1f}% быстрее чем Non-Direct Buffer", percentFasterThan2);
        }

        logger.info("\n Вывод: Для Больших файлов используй transferTo()!");
        logger.info("  Это самый быстрый способ, ОС копирует напрямую.\n");
    }
}
