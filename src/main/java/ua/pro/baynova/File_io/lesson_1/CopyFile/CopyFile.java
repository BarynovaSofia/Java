package ua.pro.baynova.File_io.lesson_1.CopyFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {
    public static void main(String[] args) {
        String sourcePath = "C:\\Users\\Dell\\Downloads\\source.jpg";
        String targetPath = "C:\\Users\\Dell\\Downloads\\copy.jpg";

        try (FileInputStream fis = new FileInputStream(sourcePath);
             FileOutputStream fos = new FileOutputStream(targetPath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("Файл успешно скопирован!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
