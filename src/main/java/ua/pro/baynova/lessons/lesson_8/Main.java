package ua.pro.baynova.lessons.lesson_8;

public class Main {
    public static void main(String[] args) {

        Book book1 = new Book("Война и мир", "Лев Толстой", 1869);
        Book book2 = new Book ("Мастер и Маргарита", "Михаил Булгаков", 1967);
        Book book3 = new Book ("Преступление и наказание", "Фёдор Достоевский", 1866);

        System.out.println(book1.title + " - " + book1.author + " (" + book1.year + ")");
        System.out.println(book2.title + " - " + book2.author + " (" + book2.year + ")");
        System.out.println(book3.title + " - " + book3.author + " (" + book3.year + ")");

    }
}
