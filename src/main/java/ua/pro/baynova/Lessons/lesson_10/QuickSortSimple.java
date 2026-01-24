package ua.pro.baynova.Lessons.lesson_10;

import java.util.Random;

public class QuickSortSimple {
    public static void main(String[] args) {
        int[] arr = new int[10];
        Random rand = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100);

        }

        System.out.println("До сортировки:");
        printArray(arr);

        quickSort(arr, 0, arr.length - 1);

        System.out.println("После сортировки:");
        printArray(arr);

    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivot = arr[right];
        int wall = left;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivot){
                swap(arr, i, wall);
                wall++;
            }
        }

        swap(arr, wall, right);
        quickSort(arr , left , wall - 1);
        quickSort(arr, wall + 1, right);
    }

    static void swap(int[] arr , int i , int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

    }

    private static void printArray(int[] arr) {
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }
}
