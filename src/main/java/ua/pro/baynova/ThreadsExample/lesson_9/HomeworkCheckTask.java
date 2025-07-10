package ua.pro.baynova.ThreadsExample.lesson_9;

import java.util.concurrent.Callable;

public class HomeworkCheckTask implements Callable<Void> {

    private final String studentName;
    private final int secondsToCheck;
    private final Sleeper sleeper;

    public HomeworkCheckTask(String studentName, int secondsToCheck, Sleeper sleeper) {
        this.studentName = studentName;
        this.secondsToCheck = secondsToCheck;
        this.sleeper = sleeper;
    }

    @Override
    public Void call() throws Exception {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " начал проверку задания от " + studentName);

        try {
            sleeper.sleep(secondsToCheck * 1000L);
        } catch (InterruptedException e) {
            System.out.println(threadName + " прервал проверку задания от " + studentName);
            throw e;
        }

        System.out.println(threadName + " завершил проверку задания от " + studentName + " ✅");
        return null;
    }
}
