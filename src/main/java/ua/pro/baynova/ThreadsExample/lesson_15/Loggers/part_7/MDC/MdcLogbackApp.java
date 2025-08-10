package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_7.MDC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MdcLogbackApp {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

       executor.submit(new TaskProcessor("USER-101"));
       executor.submit(new TaskProcessor("USER-202"));
       executor.submit(new TaskProcessor("USER-303"));

       executor.shutdown();
    }
}
