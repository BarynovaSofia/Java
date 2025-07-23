package ua.pro.baynova.ThreadsExample.lesson_13.UncaughtExceptionHandler.part_1;

public class DeliveryWorker {
    public void deliver(String packageName) {
        Thread thread = new Thread(new DeliveryTask(packageName));
        thread.setName("Курьер для " + packageName);

        thread.setUncaughtExceptionHandler((t, e) -> {
            System.err.println("⚠️ ОШИБКА! Поток " + t.getName() + " не справился: " + e.getMessage());
        });

        thread.start();
    }
}
