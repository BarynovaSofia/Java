package ua.pro.baynova.File_io.lesson_4.FileMergerPro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileMerger {
    private static final Logger logger = LoggerFactory.getLogger(FileMerger.class);

    public void mergeFiles(String firstPath, String secondPath, String outputPath) {
        int bufferSize = 16 * 1024;
        logger.info("Запуск объединения файлов с буфером {} байт", bufferSize);

        long startTime = System.nanoTime();

        try (
                BufferedReader reader1 = new BufferedReader(
                        new InputStreamReader(new FileInputStream(firstPath), StandardCharsets.UTF_8),
                        bufferSize
                );
                BufferedReader reader2 = new BufferedReader(
                        new InputStreamReader(new FileInputStream(secondPath), StandardCharsets.UTF_8),
                        bufferSize
                );
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8),
                        bufferSize
                )
                ){
            logger.info("Чтение первого файла: {}", firstPath);
            copyContent(reader1, writer);

            writer.newLine();
            writer.write("----- Конец первого файла -----");
            writer.newLine();
            writer.newLine();

            logger.info("Чтение второго файла: {}", secondPath);
            copyContent(reader2, writer);

            writer.newLine();
            writer.write("Объединение завершено!");
            writer.newLine();

            long endTime = System.nanoTime();
            long durationMs = (endTime - startTime) / 1_000_000;

            logger.info("Файлы успешно объединены в {}", outputPath);
            logger.info("Время выполнения: {} мс", durationMs);

        } catch (IOException e) {
            logger.error("Ошибка при объединении файлов: {}", e.getMessage(), e);
        }
    }

    private void copyContent(BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }
    }
}
