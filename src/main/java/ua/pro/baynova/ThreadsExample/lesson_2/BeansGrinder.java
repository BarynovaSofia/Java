package ua.pro.baynova.ThreadsExample.lesson_2;

public class BeansGrinder implements Runnable{
    @Override
    public void run(){
        System.out.println("Молим зёрна...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("✅ Зёрна смолоты.");
    }
}
