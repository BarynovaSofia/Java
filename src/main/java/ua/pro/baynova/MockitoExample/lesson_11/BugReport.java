package ua.pro.baynova.MockitoExample.lesson_11;

public class BugReport {
    private String playerId;
    private String description;

    public BugReport(String playerId, String description){
        this.playerId = playerId;
        this.description = description;
    }

    public String getPlayerId(){
        return playerId;
    }

    public String getDescription(){
        return description;
    }
}
