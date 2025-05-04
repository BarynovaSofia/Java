package ua.pro.baynova.lessons.lesson_13;

import java.util.Scanner;

public class MultipleCatchExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = {10, 20, 30, 40, 50, 60};

        try {
            System.out.println("Введите делимое: ");
            int a = scanner.nextInt();

            System.out.println("Введите делитель: ");
            int b = scanner.nextInt();

            int result = a / b;
            System.out.println("Результат деления: " + result);

            System.out.println("Введите индекс элемента массива: ");
            int index = scanner.nextInt();

            int value = arr[index];
            System.out.println("Элемент по индексу " + index + ": " + value);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: Нельзя делить на ноль.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: Индекс вне границ массива.");
        }
        System.out.println("Программа завершена.");
    }
}
