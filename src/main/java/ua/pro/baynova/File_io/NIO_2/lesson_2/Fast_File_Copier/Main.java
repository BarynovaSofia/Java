package ua.pro.baynova.File_io.NIO_2.lesson_2.Fast_File_Copier;

public class Main {
    public static void main(String[] args) {

        String source = "C:\\Users\\Dell\\IdeaProjects\\Java\\large_file.bin";
        String destDirect = "C:\\Users\\Dell\\IdeaProjects\\Java\\large_file_direct.bin";
        String destNonDirect = "C:\\Users\\Dell\\IdeaProjects\\Java\\large_file_nondirect.bin";

        FileService.compareBufferPerformance(source, destDirect, destNonDirect);
    }
}
