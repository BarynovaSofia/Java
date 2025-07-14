package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue;

public class PizzaCourier implements Runnable {
    private final PizzaQueueManager queueManager;
    private final int pizzasToDeliver;
    private final int courierId;

    public PizzaCourier(PizzaQueueManager queueManager, int pizzasToDeliver, int courierId){
        this.queueManager = queueManager;
        this.pizzasToDeliver = pizzasToDeliver;
        this.courierId = courierId;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= pizzasToDeliver; i++) {
                Pizza pizza = queueManager.takePizza();
                System.out.println("[Courier #" + courierId + "] Забрал пиццу №" + pizza.getId() + " (" + pizza.getType() + ")");
                Thread.sleep(150);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
