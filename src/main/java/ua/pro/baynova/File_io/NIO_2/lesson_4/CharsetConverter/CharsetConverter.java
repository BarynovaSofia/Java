package ua.pro.baynova.File_io.NIO_2.lesson_4.CharsetConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CharsetConverter {
    private static final Logger log = LoggerFactory.getLogger(CharsetConverter.class);

    public static void convertCharset(String inFile, String outFile, String fromCharset, String toCharset) {
        log.info("-> КОНВЕРТИРОВАНИЕ КОДИРОВКИ <-");
        log.info("📝 Исходный файл: {}", inFile);
        log.info("📊 Из кодировки: {} -> В кодировку: {}", fromCharset, toCharset);
        log.info("💾 Выходной файл: {}\n", outFile);

        if (!new File(inFile).exists()) {
            log.error("❌ Файл не найден: {}", inFile);
            return;
        }
        try {
            Charset sourceCharset = getCharset(fromCharset);
            Charset targetCharset = getCharset(toCharset);

            if (sourceCharset == null || targetCharset == null) {
                log.error("❌ Неподдерживаемая кодировка!");
                return;
            }

            log.debug("➡️ Исходная кодировка получена: {}", sourceCharset.displayName());
            log.debug("➡️ Целевая кодировка получена: {}\n", targetCharset.displayName());

            FileInputStream fis = new FileInputStream(inFile);
            InputStreamReader reader = new InputStreamReader(fis, sourceCharset);
            log.debug("➡️ InputStreamReader создан (исходная кодировка)");

            BufferedReader bufferedReader = new BufferedReader(reader);
            log.debug("➡️ BufferedReader создан");

            FileOutputStream fos = new FileOutputStream(outFile);
            OutputStreamWriter writer = new OutputStreamWriter(fos, targetCharset);
            log.debug("➡️ OutputStreamWriter создан (целевая кодировка)");

            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            log.debug("➡️ BufferedWriter создан\n");

            String line;
            int lineCount = 0;
            log.info("🔄️ Конвертирование...\n");

            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                lineCount++;

                if (lineCount % 100 == 0) {
                    log.debug("➡️ Обработано {} строк", lineCount);
                }
            }

            bufferedReader.close();
            bufferedWriter.flush();
            bufferedWriter.close();
            log.debug("✅ Потоки закрыты");

            log.info("✅ Конвертирование завершено!");
            log.info("📊 Обработано строк: {}\n", lineCount);

        } catch (FileNotFoundException e) {
            log.error("❌ Файл не найден: {}", e.getMessage());
        } catch (IOException e) {
            log.error("❌ Ошибка при чтении/записи: {}", e.getMessage());
        } catch (Exception e) {
            log.error("❌ Ошибка при конвертировании: {}", e.getMessage());
        }
    }

    public static String detectCharset(String filePath) {
        log.info("-> ОПРЕДЕЛЕНИЕ КОДИРОВКИ <-\n");
        log.info("📝 Файл: {}\n", filePath);

        try {
            byte[] content = Files.readAllBytes(Paths.get(filePath));

            if (content.length >= 3 &&
            content[0] == (byte) 0xEF &&
            content[1] == (byte) 0xBB &&
            content[2] == (byte) 0xBF) {
                log.info("🔎 Обнаружена кодировка: UTF-8 (с BOM)\n");
                return "UTF-8";
            }

            if (content.length >= 2 &&
            content[0] == (byte) 0xFF &&
            content[1] == (byte) 0xFE) {
                log.info("🔎 Обнаружена кодировка: UTF-16 LE\n");
            }

            if (content.length >= 2 &&
            content[0] == (byte) 0xFE &&
            content[1] == (byte) 0xFF) {
                log.info("🔎 Обнаружена кодировка: UTF-16 BE\n");
                return "UTF-16BE";
            }

            if (sLikelyUTF8(content)) {
                log.info("🔎 Вероятно UTF-8 (без BOM)\n");
                return "UTF-8";
            }

            if (hasWindowsCyrillic(content)) {
                log.info("🔎 Вероятно Windows-1251 (русская)\n");
                return "Windows-1251";
            }

            log.info("🔎 Не удалось определить, предполагается: UTF-8\n");
            return "UTF-8";

        } catch (IOException e) {
            log.error("❌ Ошибка при определении кодировки: {}", e.getMessage());
            return "UTF-8";
        }
    }

    public static void showFileInfo(String filePath) {
        log.info("-> ИНФОРМАЦИЯ О ФАЙЛЕ <-");
        log.info("📝 Файл: {}", filePath);

        try {
            File file = new File(filePath);
            long sizeBytes = file.length();
            long sizeKB = sizeBytes / 1024;

            String charset = detectCharset(filePath);
            int lineCount = (int) Files.lines(Paths.get(filePath), Charset.forName(charset)).count();

            log.info("💾 Размер: {} байт ({} КБ)", sizeBytes, sizeKB);
            log.info("📊 Строк: {}", lineCount);
            log.info("🔤 Предполагаемая кодировка: {}\n", charset);

        } catch (Exception e) {
            log.error("❌ Ошибка при чтении информации: {}", e.getMessage());
        }
    }

    private static Charset getCharset(String charsetName) {
        try {
            return Charset.forName(charsetName);
        } catch (Exception e) {
            log.warn("⚠️ Кодировка '{}' не поддерживается", charsetName);
            return null;
        }
    }

    private static boolean sLikelyUTF8(byte[] content) {
        int i = 0;
        while (i < content.length) {
            byte b = content[i];

            if ((b & 0x80) == 0) {
                i++;
            } else if ((b & 0xE0) == 0xC0 && i + 1 < content.length) {
                i += 2;
            } else if ((b & 0xF0) == 0xE0 && i + 2 < content.length) {
                i += 3;
            } else if ((b & 0xF8) == 0xF0 && i + 3 < content.length) {
                i += 4;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean hasWindowsCyrillic(byte[] content) {
        int cyrillicCount = 0;
        for (byte b : content) {
            if (b >= (byte) 0xC0 && b <= (byte) 0xFF) {
                cyrillicCount++;
            }
        }
        return cyrillicCount > content.length * 0.2;
    }
}
