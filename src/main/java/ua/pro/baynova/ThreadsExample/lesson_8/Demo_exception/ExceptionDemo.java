package ua.pro.baynova.ThreadsExample.lesson_8.Demo_exception;

import java.util.concurrent.*;

public class ExceptionDemo {

    private final ExecutorService executor;

    public ExceptionDemo(ExecutorService executor) {
        this.executor = executor;
    }

    public void runTasks() {
        Callable<String> goodTask = () -> "✅ Всё прошло успешно!";
        Callable<String> badTask = () -> {
            throw new IllegalArgumentException("💥 Упс, что-то пошло не так!");
        };

        Future<String> success = executor.submit(goodTask);
        Future<String> failure = executor.submit(badTask);

        try {
            System.out.println("Результат goodTask: " + success.get());
        } catch (Exception e) {
            System.out.println("goodTask - ошибка: " + e);
        }

        try {
            System.out.println("Результат badTask: " + failure.get());
        } catch (ExecutionException e) {
            System.out.println("❗ ExecutionException: " + e);
            System.out.println("🎯 getCause(): " + e.getCause());
            System.out.println("📝 getMessage(): " + e.getCause().getMessage());
        } catch (InterruptedException e) {
            System.out.println("Поток прерван");
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}