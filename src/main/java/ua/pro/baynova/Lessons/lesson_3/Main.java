package ua.pro.baynova.Lessons.lesson_3;

public class Main {
    public static void main(String[] args) {
        SimpleHashSet<String> set = new SimpleHashSet<>();

        System.out.println(set.add("apple"));
        System.out.println(set.add("banana"));
        System.out.println(set.add("apple"));

        System.out.println(set.contains("banana"));
        System.out.println(set.contains("grape"));

        System.out.println(set.size());
        System.out.println(set);

        System.out.println(set.remove("banana"));
        System.out.println(set.remove("grape"));

        System.out.println(set);
    }
}
