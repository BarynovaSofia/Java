package ua.pro.baynova.File_io.NIO_2.lesson_9.Library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.*;

class LibraryTest {
    private static final Logger log = LoggerFactory.getLogger(LibraryTest.class);

    public static void main(String[] args) throws InterruptedException {
        Library library = new Library("library.csv");

        try {
            library.clearCatalog();
        } catch (IOException e) {
            log.error("Ошибка при очистке", e);
        }

        ExecutorService executor = Executors.newFixedThreadPool(8);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        CountDownLatch latch = new CountDownLatch(10);

        log.info("--> ЗАПУСК СИМУЛЯЦИИ БИБЛИОТЕКИ <--");

        try {
            library.addBooks("1984", "Джордж Оруэлл");
            library.addBooks("О дивный новый мир", "Олдос Хаксли");
            library.addBooks("Война и мир", "Лев Толстой");
            library.addBooks("Преступление и наказание", "Федор Достоевский");
            library.addBooks("Мастер и Маргарита", "Михаил Булгаков");
        } catch (IOException e) {
            log.error("Ошибка при инициализации", e);
        }

        log.info("-> 5 ЧИТАТЕЛЕЙ СМОТРЯТ КАТАЛОГ (БЫСТРО) <-");
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                try {
                    library.getAllBooks().forEach(book ->
                            log.info("  📝 {}", book)
                    );
                } catch (IOException e) {
                    log.error("Ошибка при чтении", e);
                } finally {
                    latch.countDown();
                }
            });
        }

        log.info("--> ЗАПЛАНИРОВАННЫЕ ОПЕРАЦИИ С ЗАДЕРЖКАМИ <--");
        scheduler.schedule(() -> {
            try {
                library.rentBook("1984");
            } catch (IOException e) {
                log.error("⚠️ Ошибка", e);
            }
        }, 100, TimeUnit.MILLISECONDS);

        scheduler.schedule(() -> {
            try {
                library.rentBook("О дивный новый мир");
            } catch (IOException e) {
                log.error("⚠️ Ошибка", e);
            }
        }, 150, TimeUnit.MILLISECONDS);

        scheduler.schedule(() -> {
            try {
                library.returnBook("1984");
            } catch (IOException e) {
                log.error("Ошибка", e);
            }
        }, 250, TimeUnit.MILLISECONDS);

        scheduler.schedule(() -> {
            try {
                library.addBooks("Граф Монте-Кристо", "Александр Дюма");
            } catch (IOException e) {
                log.error("Ошибка", e);
            }
        }, 300, TimeUnit.MILLISECONDS);

        scheduler.schedule(() -> {
            try {
                library.rentBook("Война и мир");
            } catch (IOException e) {
                log.error("Ошибка", e);
            } finally {
                latch.countDown();
            }
        }, 350, TimeUnit.MILLISECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                log.info("",
                        library.getAllBooks().stream()
                                .filter(b -> !b.startsWith(""))
                                .count());
            } catch (IOException e) {
                log.error("", e);
            }
        }, 0, 2, TimeUnit.SECONDS);

        Thread.sleep(3000);

        latch.countDown();
        latch.countDown();
        latch.countDown();
        latch.countDown();
        latch.countDown();

        executor.shutdown();
        scheduler.shutdown();

        if (!executor.awaitTermination(5, TimeUnit.SECONDS)){
            executor.shutdownNow();
        }
        if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)){
            scheduler.shutdownNow();
        }

        try {
            log.info("--> ФИНАЛЬНОЕ СОСТОЯНИЕ БИБЛИОТЕКИ <--");
            library.getAllBooks().forEach(book -> log.info("  {}", book));
        } catch (IOException e) {
            log.error("⚠️ Ошибка при чтении финального состояния", e);
        }
    }
}
