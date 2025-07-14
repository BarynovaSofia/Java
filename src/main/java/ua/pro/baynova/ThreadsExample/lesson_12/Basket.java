package ua.pro.baynova.ThreadsExample.lesson_12;

public class Basket {
    private String item;

    public synchronized void put(String newItem) throws InterruptedException{
        while (item != null){
            wait();
        }
        this.item = newItem;
        System.out.println("[Producer] Кладу " + newItem + " в корзину.");
        notify();
    }

    public synchronized String take() throws InterruptedException {
        while (item == null) {
            System.out.println("[Consumer] Корзина пуста. Жду яблоко.");
            wait();
        }
        String taken = item;
        item = null;
        System.out.println("[Consumer] Забираю " + taken + " из корзины.");
        notify();
        return taken;
    }
}
