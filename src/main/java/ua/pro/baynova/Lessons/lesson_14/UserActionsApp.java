package ua.pro.baynova.Lessons.lesson_14;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserActionsApp {

    @FunctionalInterface
    interface Action{
        void doSomething();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<Integer, Action> actions = new HashMap<>();

        actions.put(1,() -> System.out.println("Привет! Хорошего дня!"));
        actions.put(2,() -> System.out.println("Пока! До скорого!"));
        actions.put(3,() -> System.out.println("Шутка: Почему программисты путают Хэллоуин и Рождество? Потому что OCT 31 = DEC 25"));

        System.out.println("Выбери действие:");
        System.out.println("1 - Поздороваться");
        System.out.println("2 - Попрощаться");
        System.out.println("3 - Рассказать шутку");

        System.out.println("Ввод: ");
        int choice = scan.nextInt();

        Action action = actions.get(choice);

        if (action != null){
            System.out.println("Выполняю действие: ");
            action.doSomething();
        } else {
            System.out.println("Нет такого действия.");
        }
    }
}
