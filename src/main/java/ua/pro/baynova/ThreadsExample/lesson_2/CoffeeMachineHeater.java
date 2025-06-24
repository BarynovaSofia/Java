package ua.pro.baynova.ThreadsExample.lesson_2;

public class CoffeeMachineHeater implements Runnable{

    @Override
    public void run(){
        System.out.println("Нагрев кофе-машины...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("✅ Кофе-машина нагрета.");
    }
}
