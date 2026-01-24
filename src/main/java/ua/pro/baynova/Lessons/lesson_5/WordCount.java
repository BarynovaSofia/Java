package ua.pro.baynova.Lessons.lesson_5;

import java.util.Map;
import java.util.TreeMap;

public class WordCount {
    public static void main(String[] args) {
        TreeMap<String, Integer> wordCountMap = new TreeMap<>();

        String text = "Java is a programming language that is used to build web applications, mobile apps, " +
                "and games. Java is versatile and powerful, and many large companies use Java for enterprise " +
                "software development.";

        String[] words = text.split(" ");

        for (String word : words){
            word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

            if (wordCountMap.containsKey(word)){
                wordCountMap.put(word, wordCountMap.get(word) + 1);
            } else {
                wordCountMap.put( word ,1);
            }
        }

        for (Map.Entry <String, Integer> entry : wordCountMap.entrySet()){
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

    }

}
