package ua.pro.baynova.lessons.lesson_8;

public class Main {
    public static void main(String[] args) {

        Book book1 = new Book("Война и мир", "Лев Толстой", 1869);
        Book book2 = new Book ("Мастер и Маргарита", "Михаил Булгаков", 1967);
        Book book3 = new Book ("Преступление и наказание", "Фёдор Достоевский", 1866);

        book1.printInfo();
        book2.printInfo();
        book3.printInfo();

    }
}
