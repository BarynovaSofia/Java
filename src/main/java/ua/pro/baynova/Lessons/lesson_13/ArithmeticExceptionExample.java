package ua.pro.baynova.Lessons.lesson_13;

import java.util.Scanner;

public class ArithmeticExceptionExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number: ");
        int number_1 = scanner.nextInt();

        System.out.println("Enter number 2: ");
        int number_2 = scanner.nextInt();

        try {
            int result = number_1 / number_2;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e){
            System.out.println("Error!!! By zero");
        }

        System.out.println("Programme completed.");

    }
}
