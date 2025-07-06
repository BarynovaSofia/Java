package ua.pro.baynova.ThreadsExample.lesson_8;

import java.util.concurrent.*;

public class ReportPrinter {

    private final ExecutorService executor;

    public ReportPrinter(ExecutorService executor) {
        this.executor = executor;
    }

    public void printReports() {
        executor.execute(() -> {
            System.out.println("🖨️ execute(): Печатаю отчёт...");
            if (true) throw new RuntimeException("Ошибка при печати через execute");
        });

        Future<?> future = executor.submit(() -> {
            System.out.println("🖨️ submit(): Печатаю отчёт...");
            if (true) throw new RuntimeException("Ошибка при печати через submit");
        });

        try {
            future.get();
        } catch (ExecutionException e) {
            System.out.println("❗ Поймали ошибку из submit(): " + e.getCause().getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
