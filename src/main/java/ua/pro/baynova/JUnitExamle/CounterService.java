package ua.pro.baynova.JUnitExamle;

public class CounterService {
    private int count = 0;

    public void increment(){
        count++;
    }

    public int getCount(){
        return count;
    }

    public void reset(){
        count = 0;
    }
}
