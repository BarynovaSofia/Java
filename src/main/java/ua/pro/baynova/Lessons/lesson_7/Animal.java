package ua.pro.baynova.Lessons.lesson_7;

public interface Animal {

    void sound();
    void eat();

    default void sleep(){
        System.out.println("Animal sleep...");
    }
}
