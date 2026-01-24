package ua.pro.baynova.File_io.lesson_4.Log_File_Cleaner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;

public class LogCleaner {
    private static final Logger logger = LoggerFactory.getLogger(LogCleaner.class);

    public void filterErrors(String sourcePath, String reportPath) {
        int errorCount = 0;
        int warningCount = 0;
        int totalLines = 0;

        try (
                BufferedReader reader = new BufferedReader(new FileReader(sourcePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(reportPath));
        ) {
            logger.info("Начало анализа лог-файла: {}", sourcePath);

            String line;
            while ((line = reader.readLine()) != null) {
                totalLines++;
                if (line.contains("ERROR")) {
                    errorCount++;
                    writer.write("[ERROR LINE] " + line);
                    writer.newLine();

                } else if (line.contains("WARNING")) {
                    warningCount++;
                    writer.write("[WARNING LINE] " + line);
                    writer.newLine();
                }
            }

            writer.newLine();
            writer.write("=== Итог анализа ===");
            writer.newLine();
            writer.write("Всего строк: " + totalLines);
            writer.newLine();
            writer.write("Ошибок (ERROR): " + errorCount);
            writer.newLine();
            writer.write("Предупреждений (WARNING): " + warningCount);
            writer.newLine();
            writer.write("Дата генерации отчёта: " + LocalDateTime.now());
            writer.newLine();

            logger.info("Анализ завершён. Ошибок: {}, предупреждений: {}", errorCount, warningCount);

        } catch (IOException e) {
            logger.error("Ошибка при обработке файла: {}", e.getMessage(), e);
        }
    }
}
