package ua.pro.baynova.File_io.lesson_1.ReadTextFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadTextFile {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\Dell\\Downloads\\text.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
