package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_3;

import java.util.concurrent.*;

public class LibraryTaskRunner {
    private final ExecutorService executor;

    public LibraryTaskRunner() {
        ThreadFactory namedFactory = runnable -> {
            Thread t = new Thread(runnable);
            t.setName("Читатель-" + t.getId());
            return t;
        };
        this.executor = Executors.newFixedThreadPool(2, namedFactory);
    }

    public void runTasks(Library library) throws InterruptedException {
        LibraryService service = new LibraryService(library);

        executor.execute(() -> {
            service.borrowBook("1984");
            service.borrowBook("Неведомая книга");
            service.borrowBook(null);
        });

        executor.execute(() -> {
            service.returnBook("1984");
            service.returnBook("Мастер и Маргарита");
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
