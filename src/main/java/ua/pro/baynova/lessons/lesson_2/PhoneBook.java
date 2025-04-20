package ua.pro.baynova.lessons.lesson_2;

import java.util.HashMap;
import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> phoneBook = new HashMap<>();

        boolean running = true;

            while (running) {
                System.out.println("\n1. Добавить контакт");
                System.out.println("2. Найти контакт");
                System.out.println("3. Удалить контакт");
                System.out.println("4. Показать все контакты");
                System.out.println("5. Выйти");
                System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.print("Введите имя: ");
                    String name = scanner.nextLine();

                    System.out.print("Введите номер телефона: ");
                    String phone = scanner.nextLine();

                    phoneBook.put(name, phone);
                    System.out.println("Контакт добавлен!");
                    break;

                case 2:
                    System.out.print("Введите имя для поиска: ");
                    String searchName = scanner.nextLine();

                    if (phoneBook.containsKey(searchName)) {
                        System.out.println("Номер телефона: " + phoneBook.get(searchName));
                    } else {
                        System.out.println("Контакт не найден.");
                    }
                    break;

                case 3:
                    System.out.print("Введите имя для удаления: ");
                    String deleteName = scanner.nextLine();

                    if (phoneBook.containsKey(deleteName)){
                        phoneBook.remove(deleteName);
                        System.out.println("Контакт удалён.");
                    } else {
                        System.out.println("Контакт не найден.");
                    }
                    break;

                case 4:
                    if (phoneBook.isEmpty()){
                        System.out.println("Телефонная книга пуста.");
                    } else {
                        System.out.println("Список контактов:");
                        for (String key : phoneBook.keySet()){
                            System.out.println(key + " -> " + phoneBook.get(key));
                        }
                    }
                    break;

                case 5:
                    System.out.println("Выход из программы...");
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
