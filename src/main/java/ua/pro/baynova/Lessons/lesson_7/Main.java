package ua.pro.baynova.Lessons.lesson_7;

public class Main {
    public static void main(String[] args) {

        Animal dog = new Dog();
        dog.sound();
        dog.eat();
        dog.sleep();

        System.out.println();

        Animal cat = new Cat();
        cat.sound();
        cat.eat();
        cat.sleep();
    }
}
