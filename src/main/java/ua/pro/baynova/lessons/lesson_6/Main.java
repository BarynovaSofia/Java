package ua.pro.baynova.lessons.lesson_6;

public class Main {
    public static void main(String[] args) {
        SearchHistory sh = new SearchHistory();

        sh.addQuery("java");
        sh.addQuery("python");
        sh.addQuery("javascript");
        sh.addQuery("kotlin");
        sh.addQuery("golang");

        System.out.println(sh.getHistory());

        sh.addQuery("java");

        System.out.println(sh.getHistory());

        sh.addQuery("scala");

        System.out.println(sh.getHistory());
    }
}