package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_3;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<String> books = new ArrayList<>();

    public Library(List<String> initialBooks) {
        books.addAll(initialBooks);
    }

    public synchronized boolean takeBook(String title) {
        return books.remove(title);
    }

    public synchronized void addBook(String title) {
        books.add(title);
    }

    public synchronized boolean hasBook(String title) {
        return books.contains(title);
    }

    public synchronized int size() {
        return books.size();
    }
}
