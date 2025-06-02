package ua.pro.baynova.MockitoExample.lesson_9;

public class PizzaOrder {
    private final PizzaService pizzaService;

    public PizzaOrder(PizzaService pizzaService){
        this.pizzaService = pizzaService;
    }

    public void makeOrder(int counter){
        for (int i = 0; i < counter; i++) {
            pizzaService.bakePizza();
        }
    }
}
