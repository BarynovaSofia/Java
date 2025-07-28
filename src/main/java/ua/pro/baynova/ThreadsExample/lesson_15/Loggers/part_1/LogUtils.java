package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_1;

import java.time.LocalTime;

public class LogUtils {
    public static void log(String workerName, String message) {
        String threadName = Thread.currentThread().getName();
        String time = java.time.LocalTime.now().withNano(0).toString();
        System.out.printf("[%s] [%s] %s: %s%n", time, threadName, workerName, message);
    }
}
