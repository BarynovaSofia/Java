package ua.pro.baynova.JUnitExamle;

public class Flashlight {
    private boolean isOn =  false;
    private int timesTurnedOn = 0;

    public void on(){
        if (!isOn){
            isOn = true;
            timesTurnedOn++;
        }
    }

    public void off(){
        isOn = false;
    }

    public boolean isOn(){
        return isOn;
    }

    public int getTimesTurnedOn(){
        return timesTurnedOn;
    }
}
