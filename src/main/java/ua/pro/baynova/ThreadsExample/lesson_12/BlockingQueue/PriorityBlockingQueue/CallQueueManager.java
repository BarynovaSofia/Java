package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.PriorityBlockingQueue;

public interface CallQueueManager {
    void addCall(EmergencyCall call) throws InterruptedException;
    EmergencyCall takeCall() throws InterruptedException;
}
