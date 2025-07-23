package ua.pro.baynova.ThreadsExample.lesson_13.UncaughtExceptionHandler.part_1;

public class DeliveryTask implements Runnable {
    private final String packageName;

    public DeliveryTask(String packageName){
        this.packageName = packageName;
    }

    @Override
    public void run() {
        System.out.println("🚚 Курьер начал доставку: " + packageName);

        if ("Посылка №3".equals(packageName)) {
            throw new RuntimeException("Упал по дороге!");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("✅ Доставлено: " + packageName);
    }
}
