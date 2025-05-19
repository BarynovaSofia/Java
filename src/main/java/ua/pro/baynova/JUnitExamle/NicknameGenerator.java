package ua.pro.baynova.JUnitExamle;

public class NicknameGenerator {

    public String generateBaseName(String name){
        return name + "_user";
    }

    public String generateNameWithNumber(String name){
        return name + (int)(Math.random() * 1000);
    }

    public String generateNameWithEmoji(String name){
        throw new UnsupportedOperationException("Ещё не реализовано");
    }
}
