package ua.pro.baynova.ThreadsExample.lesson_9;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class HomeworkReviewApp {
    public static void main(String[] args) {
        runHomeworkCheck();
    }

    public static void runHomeworkCheck() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Sleeper realSleeper = millis -> Thread.sleep(millis);

        List<Callable<Void>> tasks = Arrays.asList(
                new HomeworkCheckTask("Аня", 2, realSleeper),
                new HomeworkCheckTask("Игорь", 2, realSleeper),
                new HomeworkCheckTask("Лена", 5, realSleeper),
                new HomeworkCheckTask("Олег", 5, realSleeper),
                new HomeworkCheckTask("Таня", 4, realSleeper)
        );

        System.out.println("📘 Даём 6 секунд на проверку заданий...");

        try {
            for (Callable<Void> task : tasks) {
                executor.submit(task);
            }

            executor.shutdown();

            if (!executor.awaitTermination(6, TimeUnit.SECONDS)) {
                System.out.println("❗Некоторые проверки не завершены вовремя. Прерываем всё.");
                List<Runnable> notFinished = executor.shutdownNow();
                for (Runnable task : notFinished) {
                    System.out.println("⛔ Прервано задание: " + task);
                }
            } else {
                System.out.println("✅ Все проверки завершены вовремя!");
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("❌ Проверка прервана внешне.");
        }

        System.out.println("🔵 Сессия завершена.");
    }
}
