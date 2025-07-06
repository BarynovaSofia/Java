package ua.pro.baynova.ThreadsExample.lesson_8;

import java.util.concurrent.*;

public class ReportPrinterApp {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ReportPrinter printer = new ReportPrinter(executor);

        printer.printReports();
        printer.shutdown();
    }
}
