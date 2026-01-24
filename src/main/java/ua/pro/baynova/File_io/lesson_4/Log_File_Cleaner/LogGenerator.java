package ua.pro.baynova.File_io.lesson_4.Log_File_Cleaner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class LogGenerator {
    private static final Logger logger = LoggerFactory.getLogger(LogGenerator.class);
    private static final String[] LEVELS = {"INFO", "DEBUG", "WARNING", "ERROR"};
    private static final String[] MESSAGES = {
            "Application started",
            "User login successful",
            "Low disk space on drive C:",
            "Failed to save settings",
            "Connection timeout",
            "Memory usage exceeds 90%",
            "Database connection established",
            "Request processed successfully",
            "File not found",
            "Cache cleared",
            "Configuration loaded"
    };

    public void generatorLog(String filePath, int linesCount) {
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < linesCount; i++) {
                String level = LEVELS[random.nextInt(LEVELS.length)];
                String message = MESSAGES[random.nextInt(MESSAGES.length)];
                String logLine = String.format("[%s] %s - %s",
                        level,
                        LocalDateTime.now().minusSeconds(random.nextInt(3600)),
                        message
                );
                writer.write(logLine);
                writer.newLine();
            }

            logger.info("Сгенерирован тестовый лог-файл: {} ({} строк)", filePath, linesCount);

        } catch (IOException e) {
            logger.error("Ошибка при генерации лога: {}", e.getMessage(), e);
        }
    }
}
