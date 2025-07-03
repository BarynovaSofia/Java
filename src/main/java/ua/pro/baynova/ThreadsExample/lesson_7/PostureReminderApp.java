package ua.pro.baynova.ThreadsExample.lesson_7;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PostureReminderApp {
    public static void main(String[] args) {

        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);

        Runnable reminderTask = new PostureReminderTask(scheduled);
        System.out.println("🕒 Запускаю приложение-напоминалку...");

        scheduled.scheduleAtFixedRate(reminderTask, 5, 15, TimeUnit.SECONDS);
    }
}
