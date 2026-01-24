package ua.pro.baynova.Lessons.lesson_11;

public class BinarySearchExample {
    public static int binarySearch(int[] array, int target){
        int left = 0;
        int right = array.length - 1;

        while (left <= right){
            int middle = (left + right) / 2;
              if (array[middle] == target){
                  return middle;
              } else if (array[middle] < target) {
                  left = middle + 1;
              } else {
                  right = middle - 1;
              }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] numbers = {-3, -2, 4, 6, 7, 10, 15};
        int target = 7;

        int result = binarySearch(numbers, target);

        if (result != -1){
            System.out.println("Элемент найден на позиции: " + result);
        } else {
            System.out.println("Элемент не найден");
        }
    }
}
