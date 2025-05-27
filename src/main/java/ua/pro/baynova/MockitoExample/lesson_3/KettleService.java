package ua.pro.baynova.MockitoExample.lesson_3;

public class KettleService {
    private Kettle kettle;

    public KettleService(Kettle kettle){
        this.kettle = kettle;
    }

    public void makeTea(){
        kettle.boil();
        kettle.pour("кружка");
    }
}
