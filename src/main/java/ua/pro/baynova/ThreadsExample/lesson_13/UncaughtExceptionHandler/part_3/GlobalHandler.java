package ua.pro.baynova.ThreadsExample.lesson_13.UncaughtExceptionHandler.part_3;

public class GlobalHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("🚨 Глобальная тревога! Поток " + t.getName() + " выбросил исключение: " + e.getMessage());
    }

    @Override
    public String toString(){
        return "🚨 Общий обработчик ошибок";
    }
}
