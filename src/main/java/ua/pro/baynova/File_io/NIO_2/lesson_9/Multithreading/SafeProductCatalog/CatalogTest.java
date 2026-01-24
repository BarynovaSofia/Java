package ua.pro.baynova.File_io.NIO_2.lesson_9.Multithreading.SafeProductCatalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class CatalogTest {
    private static final Logger log = LoggerFactory.getLogger(CatalogTest.class);

    public static void main(String[] args) throws InterruptedException {
        SafeProductCatalog catalog = new SafeProductCatalog("products.csv");

        try {
            catalog.clearCatalog();
        } catch (IOException e) {
            log.error("Ошибка при очистке каталога", e);
        }

        ExecutorService executor = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(5);

        log.info("=== Запуск операций с каталогом ===");

        executor.submit(() -> {
            try {
                catalog.addProduct("iPhone 15", 85000);
                Thread.sleep(100);
                catalog.addProduct("iPad Pro", 120000);
            } catch (IOException | InterruptedException e) {
                log.error("Ошибка в задаче 1", e);
            } finally {
                latch.countDown();
            }
        });

        executor.submit(() -> {
            try {
                catalog.addProduct("Samsung S24", 75000);
                Thread.sleep(50);
                catalog.deleteProduct("iPhone 15");
            } catch (IOException | InterruptedException e) {
                log.error("Ошибка в задаче 2", e);
            } finally {
                latch.countDown();
            }
        });

        executor.submit(() -> {
            try {
                catalog.addProduct("Xiaomi 14", 45000);
                Thread.sleep(80);
                printCatalog(catalog);
            } catch (IOException | InterruptedException e) {
                log.error("Ошибка в задаче 3", e);
            } finally {
                latch.countDown();
            }
        });

        executor.submit(() -> {
            try {
                catalog.addProduct("Google Pixel 8", 79000);
            } catch (IOException e) {
                log.error("Ошибка в задаче 4", e);
            } finally {
                latch.countDown();
            }
        });

        executor.submit(() -> {
            try {
                catalog.addProduct("OnePlus 12", 55000);
                printCatalog(catalog);
            } catch (IOException e) {
                log.error("Ошибка в задаче 5", e);
            } finally {
                latch.countDown();
            }
        });

        latch.await();
        log.info("=== Все операции завершены ===");

        executor.shutdown();
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            executor.shutdownNow();
            log.warn("ExecutorService не завершился вовремя");
        }

        try {
            printCatalog(catalog);
        } catch (IOException e) {
            log.error("Ошибка при чтении финального состояния", e);
        }
    }

    private static void printCatalog(SafeProductCatalog catalog) throws IOException {
        List<String> products = catalog.getAllProducts();
        if (products.isEmpty()) {
            log.info("Каталог пуст");
        } else {
            log.info("=== КАТАЛОГ ({} товаров) ===", products.size());
            products.forEach(product -> log.info("  • {}", product));
        }
    }
}