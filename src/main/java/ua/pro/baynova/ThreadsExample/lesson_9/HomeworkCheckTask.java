package ua.pro.baynova.ThreadsExample.lesson_9;

import java.util.concurrent.Callable;

public class HomeworkCheckTask implements Callable<Void> {

    private final String studentName;
    private final int secondsToCheck;

    public HomeworkCheckTask(String studentName, int secondsToCheck) {
        this.studentName = studentName;
        this.secondsToCheck = secondsToCheck;
    }

    @Override
    public Void call() throws Exception {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " начал проверку задания от " + studentName);

        try {
            Thread.sleep(secondsToCheck * 1000L);
        } catch (InterruptedException e) {
            System.out.println(threadName + " прервал проверку задания от " + studentName);
            throw e;
        }

        System.out.println(threadName + " завершил проверку задания от " + studentName + " ✅");
        return null;
    }
}
