package ua.pro.baynova.ThreadsExample.lesson_7;

import java.util.concurrent.ScheduledExecutorService;

public class PostureReminderTask implements Runnable {

    private final ScheduledExecutorService scheduler;
    private int count = 0;

    public PostureReminderTask(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        count++;
        System.out.println("🔔 Напоминание #" + count + ": Выпрями спину!");

        if (count >= 3 && !scheduler.isShutdown()) {
            System.out.println("😌 Ок, больше не напоминаю. Но спину всё равно держи прямо!");
            scheduler.shutdown();
        }
    }
}