package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.PriorityBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityCallQueueManagerImpl implements CallQueueManager {
    private final BlockingQueue<EmergencyCall> queue;

    public PriorityCallQueueManagerImpl(){
        this.queue = new PriorityBlockingQueue<>();
    }

    @Override
    public void addCall(EmergencyCall call) throws InterruptedException {
        queue.put(call);
    }

    @Override
    public EmergencyCall takeCall() throws InterruptedException {
        return queue.take();
    }
}
