package ua.pro.baynova.JUnitExamle;

public class QuestRoom {

    public boolean solveLogicPuzzle(String answer){
        return "42".equals(answer);
    }

    public boolean passPhysicalTest(int pushUps){
        return pushUps >= 10;
    }

    public boolean solveMysticRiddle(String phrase){
        return phrase.toLowerCase().contains("тень");
    }
}
