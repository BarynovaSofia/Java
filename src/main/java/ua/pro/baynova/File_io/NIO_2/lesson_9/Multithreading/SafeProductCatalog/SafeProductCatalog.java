package ua.pro.baynova.File_io.NIO_2.lesson_9.Multithreading.SafeProductCatalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SafeProductCatalog {
    private static final Logger log = LoggerFactory.getLogger(SafeProductCatalog.class);
    private Path catalogFile;

    public SafeProductCatalog(String fileName) {
        this.catalogFile = Paths.get(fileName);
        try {
            if (!Files.exists(catalogFile)) {
                Files.createFile(catalogFile);
                log.info("Создан новый файл каталога: {}", fileName);
            }
        } catch (IOException e) {
            log.error("Не удалось создать файл каталога", e);
            throw new RuntimeException(e);
        }
    }

    public synchronized void addProduct(String name, double price) throws IOException {
        log.debug("Попытка добавления товара: {} с ценой {}", name, price);

        List<String> lines = Files.readAllLines(catalogFile);

        boolean exists = lines.stream()
                .anyMatch(line -> line.startsWith(name + ","));

        if (exists) {
            log.warn("Товар уже существует: {}", name);
            return;
        }

        lines.add(name + "," + price);
        Files.write(catalogFile, lines);
        log.info("Успешно добавлен товар: {} ({}₴)", name, price);
    }

    public synchronized void deleteProduct(String name) throws IOException {
        log.debug("Попытка удаления товара: {}", name);

        List<String> lines = Files.readAllLines(catalogFile);
        boolean removed = lines.removeIf(line -> line.startsWith(name + ","));

        if (removed) {
            Files.write(catalogFile, lines);
            log.info("Успешно удален товар: {}", name);
        } else {
            log.warn("Товар не найден для удаления: {}", name);
        }
    }

    public synchronized List<String> getAllProducts() throws IOException {
        return Files.readAllLines(catalogFile).stream()
                .filter(line -> !line.trim().isEmpty())
                .toList();
    }

    public void clearCatalog() throws IOException {
        Files.write(catalogFile, new byte[0]);
        log.info("Каталог очищен");
    }
}
