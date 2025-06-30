package ua.pro.baynova.ThreadsExample.lesson_5;

public class SupportCallTask implements Runnable{
    private final String clientName;
    private final int callDurationInSeconds;

    public SupportCallTask(String clientName, int callDurationInSeconds){
        this.clientName = clientName;
        this.callDurationInSeconds = callDurationInSeconds;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() +
                " принял звонок от " + clientName);

        try {
            Thread.sleep(callDurationInSeconds * 1000L);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("Звонок с " + clientName + " был прерван.");
            return;
        }
        System.out.println(Thread.currentThread().getName() +
                " завершил звонок с " + clientName);
    }
}
