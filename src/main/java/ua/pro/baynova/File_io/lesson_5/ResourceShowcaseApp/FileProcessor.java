package ua.pro.baynova.File_io.lesson_5.ResourceShowcaseApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileProcessor {
    private static final Logger logger = LoggerFactory.getLogger(FileProcessor.class);

    public void processFile(String inputPath, String outputPath) {
        logger.info("---Запуск обработки файлов---");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputPath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));

                DemoResource resource = new DemoResource("Кастом логгер")
                ){
            logger.info("\uD83D\uDCC2 Открыты ресурсы: {}, {}", inputPath, outputPath);

            String line;
            while ((line = reader.readLine()) != null) {
                resource.doWork();
                writer.write(">>> " + line);
                writer.newLine();

                if (line.contains("ОШИБКА")) {
                    throw new IOException("Обнаружена ошибка в тексте!");
                }
            }

            logger.info("✅ Файл успешно обработан и записан в {}" , outputPath);

        } catch (FileNotFoundException e) {
            logger.error("❌ Файл не найден: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("⚠ Ошибка при чтении/записи: {}", e.getMessage());
        } finally {
            logger.info("Блок finally: завершаем работу программы (все ресурсы уже закрыты).");
        }

        logger.info("--- Завершение метода processFile ---");
    }
}
