package ua.pro.baynova.File_io.lesson_3.Text_Analyzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextAnalyzer {

    private static final Logger logger = LoggerFactory.getLogger(TextAnalyzer.class);

    public void analyze(String sourcePath, String reportPath) {
        int lines = 0;
        int words = 0;
        int chars = 0;

        try (FileReader reader = new FileReader(sourcePath)) {
            StringBuilder currentLine = new StringBuilder();
            int symbol;

            while ((symbol = reader.read()) != -1) {
                chars++;
                char c = (char) symbol;

                if (c == '\n') {
                    lines++;
                    words += countWords(currentLine.toString());
                    currentLine.setLength(0);
                } else {
                    currentLine.append(c);
                }
            }

            if (currentLine.length() > 0) {
                lines++;
                words =+ countWords(currentLine.toString());
            }

            logger.info("Файл успешно проанализирован: {}", sourcePath);
            logger.info("Строк: {}, слов: {}, символов: {}", lines, words, chars);

        } catch (IOException e) {
            logger.error("Ошибка при чтении файла: {}", e.getMessage(), e);
            return;
        }

        try (FileWriter writer = new FileWriter(reportPath)) {
            writer.write("=== ОТЧЁТ ПО АНАЛИЗУ ФАЙЛА ===\n");
            writer.write("Исходный файл: " + sourcePath + "\n");
            writer.write("Количество строк: " + lines + "\n");
            writer.write("Количество слов: " + words + "\n");
            writer.write("Количество символов: " + chars + "\n");
            writer.write("Спасибо за использование TextAnalyzer!\n");

            logger.info("Отчёт успешно сохранён: {}", reportPath);

        } catch (IOException e) {
            logger.error("Ошибка при записи отчёта: {}", e.getMessage(), e);
        }
    }

    private int countWords(String line) {
        String cleaned = line.replaceAll("[^\\p{L}\\p{Nd}\\s]+", " ").trim();
        if (cleaned.isEmpty()) return 0;
        return cleaned.split("\\s+").length;
    }
}
