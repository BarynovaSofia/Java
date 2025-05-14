package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArraySorterTest {
    ArraySorter sorter = new ArraySorter();

    @Test
    void testSortArray(){

        int[] input = {5, 1, 4, 2, 3};
        int[] extend = {1, 2, 3, 4, 5};

        assertArrayEquals(extend, sorter.sortArray(input));
    }

    @Test
    void testAlreadySortedArray(){

        int[] input = {1, 2, 3};
        int[] extend = {1, 2, 3};

        assertArrayEquals(extend, sorter.sortArray(input));
    }

    @Test
    void testArrayWithDuplicates(){

        int[] input = {4, 2, 4, 1};
        int[] extend = {1, 2, 4, 4};

        assertArrayEquals(extend, sorter.sortArray(input));
    }


}