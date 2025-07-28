package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_1;

import java.time.LocalTime;

class Worker implements Runnable {
    private final String name;

    public Worker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        log("👷 Приступил к работе");

        try {
            Thread.sleep((long) (Math.random() * 2000));
            log("🔧 Собирает детали...");

            Thread.sleep((long) (Math.random() * 2000));

            if (Math.random() < 0.3) {
                throw new RuntimeException("🔨 Сломался инструмент!");
            }

            log("✅ Закончил работу успешно");

        } catch (InterruptedException e) {
            log("⚠️ Работа прервана: " + e.getMessage());
        } catch (Exception e) {
            log("❌ Ошибка: " + e.getMessage());
        }
    }

    private void log(String message) {
        String threadName = Thread.currentThread().getName();
        String time = LocalTime.now().withNano(0).toString();

        System.out.printf("[%s] [%s] %s: %s%n", time, threadName, name, message);
    }
}