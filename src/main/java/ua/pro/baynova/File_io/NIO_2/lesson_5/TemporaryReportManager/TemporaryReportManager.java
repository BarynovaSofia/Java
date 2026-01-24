package ua.pro.baynova.File_io.NIO_2.lesson_5.TemporaryReportManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class TemporaryReportManager {
    private static final Logger log = LoggerFactory.getLogger(TemporaryReportManager.class);

    public static Path createTemporaryReport(String reportName, List<String> data) {
        log.info(">>> СОЗДАНИЕ ВРЕМЕННОГО ОТЧЁТА <<<\n");
        log.info("(*) Название отчёта: {}", reportName);
        log.info("# Строк данных: {}\n", data.size());

        try {
            Path tempFile = Files.createTempFile("report_", ".txt");
            log.debug("-> Временный файл создан: {}", tempFile.getFileName());

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(tempFile.toFile(), StandardCharsets.UTF_8)
            );
            log.debug("# BufferedWriter создан с UTF-8");

            writer.write("=" . repeat(60) + "\n");
            writer.write("ОТЧЁТ: " + reportName + "\n");
            writer.write("Создан: " + LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            ) + "\n");

            for (int i = 0; i < data.size(); i++) {
                writer.write((i + 1) + ". " + data.get(i) + "\n");
            }

            writer.write("-" . repeat(60) + "\n\n");
            log.debug("# Данные записаны");

            writer.write("СТАТИСТИКА:\n");
            writer.write("Всего строк: " + data.size() + "\n");
            writer.write("Размер файла: " + Files.size(tempFile) + "байт\n");

            writer.flush();
            writer.close();
            log.debug("# BufferedWriter закрыт");

            log.info("-> Отчёт успешно создан!");
            log.info("# Путь: {}", tempFile);
            log.info("Размер: {} байт\n", Files.size(tempFile));

            return tempFile;

        } catch (IOException e) {
            log.error("(!!) Ошибка при создании отчёта: {}", e.getMessage());
            return null;
        }
    }

    public static Path createTemporaryDirectory(String projectName) {
        log.info(">>> СОЗДАНИЕ ВРЕМЕННОЙ ДИРЕКТОРИИ <<<");
        log.info("-> Название проекта: {}\n", projectName);

        try {
            Path tempDir = Files.createTempDirectory("report_" + projectName + "_");
            log.debug("# Временная директория создана: {}", tempDir.getFileName());

            log.info("# Директория создана!");
            log.info("-> Путь: {}\n", tempDir);

            return tempDir;

        } catch (IOException e) {
            log.error("(!!) Ошибка при создании директории: {}", e.getMessage());
            return null;
        }
    }

    public static void saveReportToDirectory(Path tempDir, String reportName, List<String> data) {
        log.info("<<< СОХРАНЕНИЕ ОТЧЁТА В ДИРЕКТОРИЮ >>>\n");
        log.info("# Директория: {}", tempDir.getFileName());
        log.info("-> Файл: {}\n", reportName);

        try {
            Path reportFile = tempDir.resolve(reportName + ".txt");
            Files.createFile(reportFile);
            log.debug("# Файл отчёта создан: {}", reportFile.getFileName());

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(reportFile.toFile(), StandardCharsets.UTF_8)
            );

            writer.write("ОТЧЁТ: " + reportName + "\n");
            writer.write("Дата: " + LocalDateTime.now() + "\n\n");

            for (String line : data) {
                writer.write(line + "\n");
            }

            writer.flush();
            writer.close();

            log.info("# Отчёт сохранён!");
            log.info("-> Путь: {}\n", reportFile);

        } catch (IOException e) {
            log.error("(!!) Ошибка при сохранении отчёта: {}", e.getMessage());
        }
    }

    public static void readTemporaryReport(Path reportFile) {
        log.info(">>> ЧТЕНИЕ ВРЕМЕННОГО ОТЧЁТА <<<\n");
        log.info("-> Файл: {}\n", reportFile.getFileName());

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(reportFile.toFile(), StandardCharsets.UTF_8)
            );
            log.debug("# BufferedReader создан");

            String line;
            int lineCount = 0;

            log.info("Содержимое отчёта:");
            log.info("");

            while ((line = reader.readLine()) != null) {
                log.info(line);
                lineCount++;
            }

            reader.close();
            log.debug("# BufferedReader закрыт");

            log.info("# Всего строк прочитано: {}\n", lineCount);
        } catch (IOException e) {
            log.error("(!!) Ошибка при чтении отчёта: {}", e.getMessage());
        }
    }

    public static void showFileInfo(Path file) {
        log.info(">>> ИНФОРМАЦИЯ О ФАЙЛЕ <<<\n");
        log.info("-> Файл: {}", file.getFileName());

        try {
            BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);

            log.info("# Размер: {} байт", attrs.size());
            log.info("-> Создан: {}", attrs.creationTime());
            log.info("#  Изменён: {}", attrs.lastModifiedTime());
            log.info("-> Абсолютный путь: {}", file.toAbsolutePath());
            log.info("->  Находится в: {}", file.getParent());
            log.info("# Существует: {}\n", Files.exists(file));

        } catch (IOException e) {
            log.error("(!!) Ошибка при чтении информации: {}", e.getMessage());
        }
    }

    public static void deleteTemporaryFile(Path file) {
        log.info(">>> УДАЛЕНИЕ ВРЕМЕННОГО ФАЙЛА <<<\n");
        log.info("-> Файл: {}\", file.getFileName()");

        try {
            if (!Files.exists(file)) {
                log.warn("<!> Файл уже удалён или не существует");
                return;
            }

            Files.delete(file);
            log.info("# Файл успешно удалён!");
            log.info("🗑-> Удалён: {}\n", file.getFileName());

        } catch (IOException e) {
            log.error("(!) Ошибка при удалении файла: {}", e.getMessage());
        }
    }

    public static void deleteTemporaryDirectory(Path dir) {
        log.info(">>> УДАЛЕНИЕ ВРЕМЕННОЙ ДИРЕКТОРИИ <<<\n");
        log.info("# Директория: {}\n", dir.getFileName());

        try {
            if (!Files.exists(dir)) {
                log.warn("<!> Директория уже удалена или не существует");
                return;
            }

            Files.walk(dir)
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            log.debug("-> Удаление: {}", path.getFileName());
                            Files.delete(path);
                        } catch (IOException e) {
                            log.error("(!) Ошибка при удалении: {}", e.getMessage());
                        }
                    });
            log.info("# Директория и всё содержимое удалены!\n");
        } catch (IOException e) {
            log.error("(!) Ошибка при удалении директории: {}", e.getMessage());
        }
    }

    public static void listTemporaryReports(Path dir) {
        log.info(">>> СПИСОК ВРЕМЕННЫХ ОТЧЁТОВ <<<\n");
        log.info("# Директория: {}\n", dir.getFileName());

        try {
            var reportFiles = Files.list(dir)
                    .filter(Files::isRegularFile)
                    .toList();

            log.info("-> Найдено файлов: {}\n", reportFiles.size());

            for (Path file : reportFiles) {
                long size = Files.size(file);
                log.info("# {} ({} байт)", file.getFileName(), size);
            }
            log.info("");

        } catch (IOException e) {
            log.error("(!) Ошибка при чтении директории: {}", e.getMessage());
        }
    }
}
