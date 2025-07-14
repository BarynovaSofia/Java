package ua.pro.baynova.ThreadsExample.lesson_12;

public class Consumer implements Runnable {
    private final Basket basket;
    private final int iterations;

    public Consumer(Basket basket, int iterations) {
        this.basket = basket;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= iterations; i++) {
                basket.take();
                Thread.sleep(300);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("[Consumer] Поток прерван");
        }
    }
}
