package ua.pro.baynova.ThreadsExample.lesson_13.UncaughtExceptionHandler.part_2;

public class GlobalCrashHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("⚠️ Глобальный перехват! Поток: " + t.getName() + " — Ошибка: " + e.getMessage());
    }
}
