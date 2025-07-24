package ua.pro.baynova.ThreadsExample.lesson_13.UncaughtExceptionHandler.part_2;

public class CallCenterAppMain {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new GlobalCrashHandler());

        Runnable operator1 = new OperatorTask("Ирина");
        Runnable operator2 = new OperatorTask("Андрей");
        Runnable operator3 = new OperatorTask("Катя");

        new Thread(operator1, "Оператор-1").start();
        new Thread(operator2, "Оператор-2").start();
        new Thread(operator3, "Оператор-3").start();
    }
}
