package ua.pro.baynova.Lessons.lesson_14;

public class Main {
    public static void main(String[] args) {
        performAction(() -> System.out.println("Привет! Я действие"));
    }

    public static void performAction(Action action){
        System.out.println("Начинаю действие...");
        action.doSomething();
        System.out.println("Действие завершено!!");
    }
}
