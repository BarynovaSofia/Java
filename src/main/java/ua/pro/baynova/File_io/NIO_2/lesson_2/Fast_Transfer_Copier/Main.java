package ua.pro.baynova.File_io.NIO_2.lesson_2.Fast_Transfer_Copier;

public class Main {
    public static void main(String[] args) {
        String source = "C:\\Users\\Dell\\IdeaProjects\\Java\\large_file.bin";
        String destDirect = "C:\\Users\\Dell\\IdeaProjects\\Java\\copy_direct.bin";
        String destNonDirect = "C:\\Users\\Dell\\IdeaProjects\\Java\\copy_nondirect.bin";
        String destTransfer = "C:\\Users\\Dell\\IdeaProjects\\Java\\copy_transfer.bin";

        TransferService.compareAllMethods(source, destDirect, destNonDirect, destTransfer);
    }
}
