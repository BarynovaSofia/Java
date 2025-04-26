package ua.pro.baynova.lessons.lesson_7;

public class Dog implements Animal{
    @Override
    public void sound() {
        System.out.println("wof wof!!!");
    }

    @Override
    public void eat() {
        System.out.println("eat meet...");
    }

    @Override
    public void sleep() {
        System.out.println("sleeps in a booth...");
    }
}
