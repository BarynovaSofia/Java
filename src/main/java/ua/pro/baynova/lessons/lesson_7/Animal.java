package ua.pro.baynova.lessons.lesson_7;

public interface Animal {

    void sound();
    void eat();

    default void sleep(){
        System.out.println("Animal sleep...");
    }
}
