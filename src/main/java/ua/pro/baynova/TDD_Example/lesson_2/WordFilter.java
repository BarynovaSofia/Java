package ua.pro.baynova.TDD_Example.lesson_2;

import java.util.ArrayList;
import java.util.List;

public class WordFilter {
    public static List<String> filterWords (String sentence, int minLength, char startsWith){
        char lowerStart = Character.toLowerCase(startsWith);
        String[] words = sentence.split(" ");
        List<String> result = new ArrayList<>();
        for (String word : words){
            if (word.length() >= minLength &&
                    Character.toLowerCase(word.charAt(0)) == lowerStart) {
                result.add(word);
            }
        }
        return result;
    }
}
