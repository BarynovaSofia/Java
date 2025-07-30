package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_3;

import java.util.logging.Logger;

public class LibraryService {
    private static final Logger logger = Logger.getLogger(LibraryService.class.getName());
    private final Library library;

    public LibraryService(Library library) {
        this.library = library;
        logger.config("Инициализирована библиотека: " + library.size() + " книг");
    }

    public void borrowBook(String title) {
        logger.info("Попытка взять книгу: " + title);

        if (title == null || title.isBlank()) {
            logger.severe("Название книги не указано");
            return;
        }

        logger.fine("Поиск книги: " + title);

        if (library.takeBook(title)) {
            logger.info("Книга выдана: " + title);
        } else {
            logger.warning("Книга не найдена: " + title);
        }
    }

    public void returnBook(String title) {
        logger.info("Возврат книги: " + title);
        library.addBook(title);
        logger.info("Книга успешно возвращена: " + title);
    }
}

