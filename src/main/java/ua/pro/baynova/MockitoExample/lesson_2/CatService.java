package ua.pro.baynova.MockitoExample.lesson_2;

public class CatService {
    Cat cat;

    public CatService(Cat cat){
        this.cat = cat;
    }

    public String makeCatSpeak(){
        return cat.speak();
    }
}
