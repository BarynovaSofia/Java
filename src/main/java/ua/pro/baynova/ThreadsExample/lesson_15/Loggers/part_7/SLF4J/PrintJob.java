package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_7.SLF4J;

public class PrintJob {
    private final String documentName;
    private final String employeeName;
    private final int pageCount;

    public PrintJob(String documentName, String employeeName, int pageCount) {
        this.documentName = documentName;
        this.employeeName = employeeName;
        this.pageCount = pageCount;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getPageCount() {
        return pageCount;
    }

    @Override
    public String toString() {
        return String.format("Document: '%s', From: %s, Pages: %d",
                documentName, employeeName, pageCount);
    }
}

