package ua.pro.baynova.ThreadsExample.lesson_8.Demo_exception;

import java.util.concurrent.*;

public class ExceptionDemoApp {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ExceptionDemo demo = new ExceptionDemo(executor);

        demo.runTasks();
        demo.shutdown();
    }
}
