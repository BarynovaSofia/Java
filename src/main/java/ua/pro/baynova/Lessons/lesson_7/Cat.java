package ua.pro.baynova.Lessons.lesson_7;

public class Cat implements Animal{
    @Override
    public void sound() {
        System.out.println("meow meow!!!");
    }

    @Override
    public void eat() {
        System.out.println("eat fish...");
    }
}
