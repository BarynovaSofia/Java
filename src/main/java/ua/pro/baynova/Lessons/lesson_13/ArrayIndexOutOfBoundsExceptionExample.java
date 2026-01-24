package ua.pro.baynova.Lessons.lesson_13;

import java.util.Scanner;

public class ArrayIndexOutOfBoundsExceptionExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = {2, 3, 6};

        System.out.println("Enter index from 0 to 2: ");
        int index = scanner.nextInt();

        try {
            System.out.println("Element: " + arr[index]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Error: You have exceeded the array boundaries!");
        }

        System.out.println("Programme completed.");

    }
}
