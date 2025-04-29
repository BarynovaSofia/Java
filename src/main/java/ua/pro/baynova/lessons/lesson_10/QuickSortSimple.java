package ua.pro.baynova.lessons.lesson_10;

public class QuickSortSimple {
    public static void main(String[] args) {

        int[] arr = {3, 5, 2, 9, 7, 4, 10};
        quickSort(arr, 0, arr.length - 1);

        for (int num : arr) {
            System.out.print(num + " ");
        }
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
}
