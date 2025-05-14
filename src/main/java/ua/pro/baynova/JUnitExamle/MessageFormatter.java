package ua.pro.baynova.JUnitExamle;

public class MessageFormatter {

    public String formatMessage(String date, String author, String text){
        return date + " - " + author + ": " + text;
    }
}
