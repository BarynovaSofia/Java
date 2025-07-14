package ua.pro.baynova.ThreadsExample.lesson_12;

public class Producer implements Runnable{
    private final Basket basket;
    private final int iterations;

    public Producer(Basket basket, int iterations){
        this.basket = basket;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= iterations ; i++) {
                basket.put("яблоко");
                Thread.sleep(200);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("[Producer] Поток прерван");
        }
    }
}
