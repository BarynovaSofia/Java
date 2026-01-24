package ua.pro.baynova.File_io.lesson_3.Text_Analyzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class TextAnalyzerApp {

    private static final Logger logger = LoggerFactory.getLogger(TextAnalyzerApp.class);

    public static void main(String[] args) {
        logger.info("=== Запуск TextAnalyzer ===");

        Scanner scanner = new Scanner(System.in);
        TextAnalyzer analyzer = new TextAnalyzer();

        System.out.println("Введите путь к исходному файлу: ");
        String source = scanner.nextLine();

        System.out.println("Введите путь для отчёта: ");
        String report = scanner.nextLine();

        analyzer.analyze(source, report);

        logger.info("=== Завершение работы ===");
    }
}
