package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue.PriorityBlockingQueue;

public class CallOperator implements Runnable {
    private final CallQueueManager callQueueManager;
    private final String operatorName;
    private final int callsToHandle;
    
    public CallOperator(CallQueueManager callQueueManager, String operatorName, int callsToHandle){
        this.callQueueManager = callQueueManager;
        this.operatorName = operatorName;
        this.callsToHandle = callsToHandle;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < callsToHandle; i++) {
                EmergencyCall call = callQueueManager.takeCall();
                System.out.printf("Оператор %s обрабатывает вызов: %s (приоритет %d)%n",
                        operatorName, call.getDescription(), call.getPriority());
                Thread.sleep(150);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
