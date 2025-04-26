package ua.pro.baynova.lessons.lesson_8;

public class Book {

    String title;
    String author;
    int year;

    public Book(String title, String author, int year){
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public void printInfo(){
        System.out.println(title + " - " + author + " (" + year + ")");
    }
}
