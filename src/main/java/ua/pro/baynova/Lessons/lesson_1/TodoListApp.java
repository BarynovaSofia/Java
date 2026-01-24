package ua.pro.baynova.Lessons.lesson_1;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoListApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Показать все задачи");
            System.out.println("3. Удалить задачу");
            System.out.println("4. Очистить список");
            System.out.println("5. Выйти");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите задачу: ");
                    String task = scanner.nextLine();
                    tasks.add(task);
                    break;
                case 2:
                    if (tasks.isEmpty()) {
                        System.out.println("Список задач пуст.");
                    } else {
                        System.out.println("Список задач:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + 1 + ". " + tasks.get(i));
                        }
                    }
                    break;
                case 3:
                    if (tasks.isEmpty()) {
                        System.out.println("Список задач пуст, нечего удалять.");
                    } else {
                        System.out.print("Введите номер задачи для удаления: ");
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        if (index >= 1 && index <= tasks.size()) {
                            tasks.remove(index - 1);
                            System.out.println("Задача удалена.");
                        } else {
                            System.out.println("Некорректный номер задачи.");
                        }
                    }
                    break;
                case 4:
                    tasks.clear();
                    System.out.println("Список задач очищен.");
                    break;

                case 5:
                    System.out.println("Выход из программы. Пока!");
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }
}
