package ua.pro.baynova.TDD_Example.lesson_2;

import java.util.List;

public class NumberUtils {
    public static int sumEvenNumbers(List<Integer> numbers){
        int sum = 0;
        for (int num : numbers){
            if (num % 2 == 0){
                sum += num;
            }
        }
        return sum;
    }
}
