package ua.pro.baynova.ThreadsExample.lesson_13.UncaughtExceptionHandler.part_3;

public class CallCenterAppMain {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new GlobalHandler());

        Thread maria = new Thread(new OperatorTask("Мария", true), "Мария");
        maria.setUncaughtExceptionHandler(new PersonalHandler("Куратор Олег"));

        Thread igor = new Thread(new OperatorTask("Игорь", false), "Игорь");
        Thread sveta = new Thread(new OperatorTask("Света", true), "Света");

        maria.start();
        igor.start();
        sveta.start();
    }
}
