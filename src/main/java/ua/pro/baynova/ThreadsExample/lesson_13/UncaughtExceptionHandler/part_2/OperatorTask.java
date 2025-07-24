package ua.pro.baynova.ThreadsExample.lesson_13.UncaughtExceptionHandler.part_2;

public class OperatorTask implements Runnable {
    private final String name;

    public OperatorTask(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("📞 Оператор " + name + " начал разговор.");
        if (Math.random() < 0.5){
            System.out.println("Перегрузка линии у оператора " + name);
        }
        System.out.println("✅ Оператор " + name + " успешно завершил разговор.");
    }
}