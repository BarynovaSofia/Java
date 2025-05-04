package ua.pro.baynova.lessons.lesson_13;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiCatchExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = {100, 200, 400, 600, 700};

        try {
            System.out.println("Введите индекс элемента массива: ");
            int a = scanner.nextInt();

            System.out.println("Введите делитель: ");
            int b = scanner.nextInt();

            int result = arr[a] / b;
            System.out.println("Результат: " + result);

        } catch (ArithmeticException | ArrayIndexOutOfBoundsException | InputMismatchException e){
            System.out.println("Ошибка: некорректный ввод или операция!");
        }
        System.out.println("Программа завершена.");
    }
}
