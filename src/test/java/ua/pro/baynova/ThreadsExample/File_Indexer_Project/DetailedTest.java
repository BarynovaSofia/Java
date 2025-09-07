package ua.pro.baynova.ThreadsExample.File_Indexer_Project;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class DetailedTest {

    public static void main(String[] args) throws Exception {
        System.out.println("=== ПОДРОБНЫЙ РАЗБОР МЕТОДОВ ===\n");

        demonstrateCountDownLatch();
        demonstrateConcurrentHashMap();
        demonstrateOptional();
        demonstrateCallableAndFuture();
        demonstrateCompleteExample();
    }

    private static void demonstrateCountDownLatch() throws InterruptedException {
        System.out.println("--- 1. CountDownLatch ---");
        System.out.println("CountDownLatch - это синхронизатор, который позволяет одному потоку");
        System.out.println("ждать завершения определенного количества операций в других потоках\n");

        CountDownLatch latch = new CountDownLatch(3);
        System.out.println("✅ Создали CountDownLatch(3) - ждем 3 операции");

        for (int i = 1; i <= 3; i++) {
            int taskNumber = i;
            new Thread(() -> {
                try {
                    System.out.printf("[%s] Поток %d начал работу%n",
                            Thread.currentThread().getName(), taskNumber);

                    Thread.sleep(1000 + taskNumber * 500);

                    System.out.printf("[%s] Поток %d завершил работу%n",
                            Thread.currentThread().getName(), taskNumber);

                    latch.countDown();
                    System.out.printf("⬇️ countDown() вызван, осталось: %d%n", latch.getCount());

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Worker-" + i).start();
        }

        System.out.println("⏳ Главный поток вызывает latch.await() - ждем пока счетчик станет 0");
        latch.await();
        System.out.println("🎉 Все потоки завершились! Продолжаем работу\n");
    }

    private static void demonstrateConcurrentHashMap() throws InterruptedException {
        System.out.println("--- 2. ConcurrentHashMap ---");
        System.out.println("ConcurrentHashMap - потокобезопасная версия HashMap");
        System.out.println("Можно безопасно использовать из множества потоков одновременно\n");

        ConcurrentHashMap<String, Integer> safeMap = new ConcurrentHashMap<>();
        System.out.println("✅ Создали ConcurrentHashMap<String, Integer>");

        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 1; i <= 3; i++) {
            int threadNum = i;
            new Thread(() -> {
                safeMap.put("thread-" + threadNum, threadNum * 10);
                System.out.printf("[%s] Добавил: thread-%d = %d%n",
                        Thread.currentThread().getName(), threadNum, threadNum * 10);

                safeMap.computeIfAbsent("shared", k -> {
                    System.out.printf("[%s] Создаю 'shared' ключ%n", Thread.currentThread().getName());
                    return 100;
                });

                latch.countDown();
            }, "MapWriter-" + i).start();
        }

        latch.await();

        System.out.println("\n📋 Содержимое ConcurrentHashMap:");
        safeMap.forEach((key, value) -> {
            System.out.printf("  %s = %d%n", key, value);
        });

        System.out.printf("📊 Размер мапы: %d элементов%n", safeMap.size());
        System.out.println();
    }

    private static void demonstrateOptional() {
        System.out.println("--- 3. Optional ---");
        System.out.println("Optional - это контейнер, который может содержать значение или быть пустым");
        System.out.println("Помогает избежать NullPointerException\n");

        Optional<String> presentValue = Optional.of("Hello World");
        Optional<String> emptyValue = Optional.empty();
        Optional<String> nullableValue = Optional.ofNullable(null);

        System.out.println("✅ Создали 3 Optional:");
        System.out.printf("  presentValue.isPresent() = %b%n", presentValue.isPresent());
        System.out.printf("  emptyValue.isEmpty() = %b%n", emptyValue.isEmpty());
        System.out.printf("  nullableValue.isPresent() = %b%n", nullableValue.isPresent());

        System.out.println("\n🔍 Получаем значения:");

        presentValue.ifPresent(value ->
                System.out.println("  Есть значение: " + value));

        emptyValue.ifPresent(value ->
                System.out.println("  Есть значение: " + value));

        String result1 = presentValue.orElse("Значения нет");
        String result2 = emptyValue.orElse("Значения нет");

        System.out.printf("  presentValue.orElse() = '%s'%n", result1);
        System.out.printf("  emptyValue.orElse() = '%s'%n", result2);

        Optional<Integer> length = presentValue.map(String::length);
        System.out.printf("  Длина строки: %s%n", length.orElse(0));
        System.out.println();
    }

    private static void demonstrateCallableAndFuture() throws Exception {
        System.out.println("--- 4. Callable и Future ---");
        System.out.println("Callable - задача которая возвращает результат (в отличие от Runnable)");
        System.out.println("Future - 'обещание' результата, который будет получен в будущем\n");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        System.out.println("✅ Создали ExecutorService с 2 потоками");

        Callable<String> task1 = () -> {
            Thread.sleep(1000);
            return "[Задача 1] Результат после 1 секунды";
        };

        Callable<Integer> task2 = () -> {
            Thread.sleep(2000);
            return 42;
        };

        System.out.println("📤 Отправляем задачи в ExecutorService:");

        Future<String> future1 = executor.submit(task1);
        Future<Integer> future2 = executor.submit(task2);

        System.out.println("  - Задача 1 отправлена, получили Future<String>");
        System.out.println("  - Задача 2 отправлена, получили Future<Integer>");

        System.out.printf("⏳ Задача 1 завершена? %b%n", future1.isDone());
        System.out.printf("⏳ Задача 2 завершена? %b%n", future2.isDone());

        System.out.println("⏳ Вызываем future1.get() - ждем результат...");
        String result1 = future1.get();
        System.out.println("✅ Получили: " + result1);

        System.out.println("⏳ Вызываем future2.get() - ждем результат...");
        Integer result2 = future2.get();
        System.out.println("✅ Получили: " + result2);

        executor.shutdown();
        System.out.println("🛑 ExecutorService завершен\n");
    }

    private static void demonstrateCompleteExample() throws Exception {
        System.out.println("--- 5. Полный пример с HashCalculator ---");
        System.out.println("Показываем как Callable, Future, ConcurrentHashMap работают вместе\n");

        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        System.out.printf("📁 Используем временную папку: %s%n", tempDir.getAbsolutePath());

        File[] files = tempDir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("❌ Нет файлов для тестирования");
            return;
        }

        File[] testFiles = Arrays.copyOf(files, Math.min(3, files.length));
        System.out.printf("📋 Будем хешировать %d файлов:%n", testFiles.length);
        for (File f : testFiles) {
            System.out.printf("  - %s (%d байт)%n", f.getName(), f.length());
        }

        ExecutorService executor = Executors.newFixedThreadPool(2);
        ConcurrentHashMap<String, String> results = new ConcurrentHashMap<>();

        List<Future<String>> futures = new ArrayList<>();
        System.out.println("\n🚀 Запускаем хеширование:");

        for (File file : testFiles) {
            HashCalculator calculator = new HashCalculator(file);
            Future<String> future = executor.submit(calculator);
            futures.add(future);
        }

        System.out.println("📥 Собираем результаты:");
        for (int i = 0; i < futures.size(); i++) {
            Future<String> future = futures.get(i);
            File file = testFiles[i];

            try {
                String hash = future.get();
                results.put(file.getName(), hash);
                System.out.printf("✅ %s -> %s...%n", file.getName(), hash.substring(0, 16));
            } catch (Exception e) {
                System.err.printf("❌ Ошибка для %s: %s%n", file.getName(), e.getMessage());
            }
        }

        System.out.printf("\n📊 Итого обработано файлов: %d%n", results.size());
        System.out.println("🎉 Пример завершен!");

        executor.shutdown();
    }
}
