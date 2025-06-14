package ua.pro.baynova.TDD_Example.lesson_3;

import java.util.*;

public class DuplicateRemover {
    public static List<String> removeDuplicates(List<String> input){
        List<String> result = new ArrayList<>();
        Set<String> seenLowercase = new HashSet<>();

        for (String word : input){
            String lower = word.toLowerCase();
            if (!seenLowercase.contains(lower)){
                seenLowercase.add(lower);
                result.add(word);
            }
        }
        return result;
    }
}
