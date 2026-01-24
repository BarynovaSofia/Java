package ua.pro.baynova.File_io.NIO_2.lesson_4.CharsetConverter;

import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String utf8File = "C:\\Users\\Dell\\IdeaProjects\\Java\\text_utf8.txt";
        String windows1251File = "C:\\Users\\Dell\\IdeaProjects\\Java\\text_windows1251.txt";
        String convertedUTF8 = "C:\\Users\\Dell\\IdeaProjects\\Java\\converted_to_utf8.txt";
        String convertedWindows = "C:\\Users\\Dell\\IdeaProjects\\Java\\converted_to_windows1251.txt";

        createTestFiles(utf8File, windows1251File);
        log.info("\n");

        log.info("<- ИНФОРМАЦИЯ О ФАЙЛАХ ->\n");
        CharsetConverter.showFileInfo(utf8File);
        CharsetConverter.showFileInfo(windows1251File);

        log.info("<- ОПРЕДЕЛЕНИЕ КОДИРОВОК ->\n");
        String detectedUTF8 = CharsetConverter.detectCharset(utf8File);
        String detectedWin1251 = CharsetConverter.detectCharset(windows1251File);

        log.info("\n");
        CharsetConverter.convertCharset(windows1251File, convertedUTF8,
                "Windows-1251", "UTF-8");

        log.info("\n");
        CharsetConverter.convertCharset(utf8File, convertedWindows,
                "UTF-8", "Windows-1251");

        log.info("<- ИНФОРМАЦИЯ О ПРЕОБРАЗОВАННЫХ ФАЙЛАХ ->\n");
        CharsetConverter.showFileInfo(convertedUTF8);
        CharsetConverter.showFileInfo(convertedWindows);
    }

    private static void createTestFiles(String utf8File, String windows1251File) {
        log.info("<- СОЗДАНИЕ ТЕСТОВЫХ ФАЙЛОВ ->\n");

        String[] textLine = {
                "Привет, мир!",
                "Это тестовый файл для демонстрации кодировок",
                "Русский текст: А, Б, В, Г, Д",
                "Latin text: A, B, C, D, E",
                "Специальные символы: €, £, ¥, © ®",
                "Ещё одна строка на русском языке"
        };

        try {
            FileWriter writer = new FileWriter(utf8File, StandardCharsets.UTF_8);
            for (String line : textLine) {
                writer.write(line + "\n");
            }
            writer.close();
            log.info("✅ Создан файл в UTF-8: {}", utf8File);
        } catch (Exception e) {
            log.error("❌ Ошибка при создании UTF-8 файла: {}", e.getMessage());
        }

        try {
            FileWriter writer = new FileWriter(windows1251File,
                    java.nio.charset.Charset.forName("Windows-1251"));
            for (String line : textLine) {
                if (!line.contains("€") && !line.contains("£") && !line.contains("¥")) {
                    writer.write(line + "\n");
                }
            }
            writer.close();
            log.info("✅ Создан файл в Windows-1251: {}", windows1251File);
        } catch (Exception e) {
            log.error("❌ Ошибка при создании Windows-1251 файла: {}", e.getMessage());
        }

        log.info("\n");
    }
}
