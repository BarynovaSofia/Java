package ua.pro.baynova.File_io.NIO_2.lesson_9.Library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;

public class Library {
    private static final Logger log = LoggerFactory.getLogger(Library.class);

    private Path catalogFile;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public Library(String fileName) {
        this.catalogFile = Paths.get(fileName);
        try {
            if (!Files.exists(catalogFile)){
                Files.createFile(catalogFile);
                log.info("Создан новый каталог библиотеки: {}", fileName);
            }
        } catch (IOException e) {
            log.error("Ошибка при создании файла каталога", e);
            throw new RuntimeException(e);
        }
    }

    private <T> T readSafely(Function<List<String>, T> operation) throws IOException {
        lock.readLock().lock();
        try {
            List<String> books = Files.readAllLines(catalogFile).stream()
                    .filter(line -> !line.trim().isEmpty())
                    .toList();
            return operation.apply(books);
        } finally {
            lock.readLock().unlock();
        }
    }

    private void writeSafely(Function<List<String>, List<String>> operation) throws IOException {
        lock.writeLock().lock();
        try {
            List<String> books = new ArrayList<>(Files.readAllLines(catalogFile).stream()
                    .filter(line -> !line.trim().isEmpty())
                    .toList());

            List<String> updated = operation.apply(books);
            Files.write(catalogFile, updated);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public List<String> getAllBooks() throws IOException {
        return readSafely(books -> {
            log.info("🔄️ Поток {} прочитал {} книг", Thread.currentThread().getName(), books.size());
            return books;
        });
    }

    public boolean bookExists(String title) throws IOException {
        return readSafely(books -> {
            boolean exists = books.stream()
                    .anyMatch(line -> line.contains(title) && !line.startsWith("АРЕНДОВАНА:"));
            log.info("Книга '{}' {} доступна", title, exists ? "✅" : "❌");
            return exists;
        });
    }

    public void rentBook(String title) throws IOException {
        writeSafely(books -> {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).contains(title) && !books.get(i).startsWith("АРЕНДОВАНА:")) {
                    String rental = "АРЕНДОВАНА: " + title + " [" + System.currentTimeMillis() + "]";
                    books.set(i, rental);
                    log.info("✅ Книга '{}' арендована", title);
                    return books;
                }
            }
            log.warn("❌ Книга '{}' недоступна", title);
            return books;
        });
    }

    public void returnBook(String title) throws IOException {
        writeSafely(books -> {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).startsWith("АРЕНДОВАНА:") && books.get(i).contains(title)) {
                    books.set(i, title);
                    log.info("✅ Книга '{}' возвращена", title);
                    return books;
                }
            }
            log.warn("❌ Книга '{}' не найдена в арендованных", title);
            return books;
        });
    }

    public void addBooks(String title, String author) throws IOException {
        writeSafely(books -> {
            boolean exists = books.stream()
                    .anyMatch(line -> line.contains(title));

            if (exists) {
                log.warn("⚠️ Книга '{}' уже существует", title);
            } else {
                books.add(title + " | " + author);
                log.info("➡️ Добавлена: '{}' ({})", title, author);
            }
            return books;
        });
    }

    public void clearCatalog() throws IOException {
        lock.writeLock().lock();
        try {
            Files.write(catalogFile, new byte[0]);
            log.info("Каталог очищен");
        } finally {
            lock.writeLock().unlock();
        }
    }
}
