package ua.pro.baynova.lessons.lesson_9;

public class MergeSortMethod {
    public static void main(String[] args) {
        int[] merge = {2, 6, 9, 3, 5, 7};
        mergeSort(merge);

        for (int num : merge) {
            System.out.print(num + " ");
        }
    }

    private static void mergeSort(int[] merge) {
        if (merge.length <= 1){
            return;
        }
        int mid = merge.length / 2;
        int[] left = new int[mid];
        int[] right = new int[merge.length - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = merge[i];
        }
        for (int i = mid; i < merge.length; i++) {
            right[i - mid] = merge[i];
        }

        mergeSort(left);
        mergeSort(right);

        mergeMethod(merge, left, right);
    }

    public static void mergeMethod(int[] merge , int[] left, int[] right){
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length){
            if (left[i] <= right[j]){
                merge[k++] = left[i++];
            } else {
                merge[k++] = right[j++];
            }
        }

        while (i < left.length){
            merge[k++] = left[i++];
        }

        while (j < right.length){
            merge[k++] = right[j++];
        }
    }
}
