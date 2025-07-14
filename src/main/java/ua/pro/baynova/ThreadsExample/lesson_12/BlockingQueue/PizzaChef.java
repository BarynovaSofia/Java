package ua.pro.baynova.ThreadsExample.lesson_12.BlockingQueue;

public class PizzaChef implements Runnable {
    private final PizzaQueueManager queueManager;
    private final int pizzasToCook;
    private final String type;
    private final int chefId;

    public PizzaChef(PizzaQueueManager queueManager, int pizzasToCook, String type, int chefId){
        this.queueManager = queueManager;
        this.pizzasToCook = pizzasToCook;
        this.type = type;
        this.chefId = chefId;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= pizzasToCook ; i++) {
                Pizza pizza = new Pizza(i, type);
                queueManager.putPizza(pizza);
                System.out.println("[Chef #" + chefId + "] Приготовил пиццу №" + pizza.getId() + " (" + pizza.getType() + ")");
                Thread.sleep(100);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
