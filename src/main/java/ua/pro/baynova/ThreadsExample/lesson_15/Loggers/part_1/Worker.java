package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_1;

import java.time.LocalTime;

class Worker implements Runnable {
    private final String name;

    public Worker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        LogUtils.log(name, "\uD83D\uDC77 Приступил к работе");
        try {
            Thread.sleep(1000);
            LogUtils.log(name, "🔧 Собирает детали...");
            Thread.sleep(1000);
            LogUtils.log(name, "✅ Закончил работу успешно");
        } catch (InterruptedException e){
            LogUtils.log(name, "❌ Работа прервана: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}