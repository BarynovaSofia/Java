package ua.pro.baynova.ThreadsExample.lesson_13.UncaughtExceptionHandler.part_3;

public class OperatorTask implements Runnable {
    private final String name;
    public final boolean willFail;

    public OperatorTask(String name, boolean willFail){
        this.name = name;
        this.willFail = willFail;
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();

        System.out.println("📞 Оператор " + name + " начал разговор.");
        System.out.println("🧾 getUncaughtExceptionHandler(): " + current.getUncaughtExceptionHandler());
        System.out.println("🧾 getDefaultUncaughtExceptionHandler(): " + Thread.getDefaultUncaughtExceptionHandler());

        try {
            Thread.sleep(500);
            if (willFail){
                throw new RuntimeException("Связь с клиентом утеряна!");
            }
            System.out.println("✅ Оператор " + name + " успешно завершил разговор.");
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
