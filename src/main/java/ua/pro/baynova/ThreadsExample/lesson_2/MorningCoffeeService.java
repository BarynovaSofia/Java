package ua.pro.baynova.ThreadsExample.lesson_2;

public class MorningCoffeeService {

    public void makeCoffee() throws InterruptedException {

        Thread heater = new Thread(new CoffeeMachineHeater());
        Thread grinder = new Thread(new BeansGrinder());
        Thread maker = new Thread(new CoffeeMaker());

        heater.start();
        heater.join();

        grinder.start();
        grinder.join();

        maker.start();
        maker.join();
    }
}
