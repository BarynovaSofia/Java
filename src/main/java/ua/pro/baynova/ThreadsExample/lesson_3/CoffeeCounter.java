package ua.pro.baynova.ThreadsExample.lesson_3;

public class CoffeeCounter {
    private int counter = 0;

    public synchronized void increment(){
        counter++;
    }

    public int getCounter(){
        return counter;
    }
}
