package ua.pro.baynova.lessons.lesson_13;

import java.io.FileReader;
import java.io.IOException;

public class ThrowsExample {
    public static void main(String[] args) {

        try {
            fileReader();
            System.out.println("Файл успешно прочитан!");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

    }

    public static void fileReader() throws IOException {
        FileReader reader = new FileReader("data.txt");

    }
}
