package ua.pro.baynova.ThreadsExample.lesson_2;

public class CoffeeMaker implements Runnable{

    @Override
    public void run(){
        System.out.println("Готовим кофе...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("✅ Кофе готов!");
    }
}
