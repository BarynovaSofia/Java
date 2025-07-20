package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.PriorityBlockingQueue;

public class EmergencyDispatcher implements Runnable {
    private final CallQueueManager queueManager;
    private final int totalCalls;
    private final String[] callers = {"Alice", "Bob", "Charlie", "Dave", "Eve"};

    public EmergencyDispatcher(CallQueueManager queueManager, int totalCalls) {
        this.queueManager = queueManager;
        this.totalCalls = totalCalls;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= totalCalls; i++) {
                String caller = callers[i % callers.length];
                int priority = (int) (Math.random() * 5) + 1; // от 1 до 5
                EmergencyCall call = new EmergencyCall(priority, caller + "_Call" + i);
                queueManager.addCall(call);
                System.out.printf("Поступил вызов: %s с приоритетом %d%n", call.getDescription(), priority);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
