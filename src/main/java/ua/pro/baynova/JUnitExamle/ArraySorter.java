package ua.pro.baynova.JUnitExamle;

import java.util.Arrays;

public class ArraySorter {

    public int[] sortArray (int[] input){
        int[] copy = Arrays.copyOf(input, input.length);

        Arrays.sort(copy);
        return copy;
    }
}
