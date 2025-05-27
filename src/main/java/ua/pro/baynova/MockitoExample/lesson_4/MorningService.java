package ua.pro.baynova.MockitoExample.lesson_4;

public class MorningService {

    private final AlarmClock alarmClock;
    private final CoffeeMachine coffeeMachine;

    public MorningService(AlarmClock alarmClock, CoffeeMachine coffeeMachine){
        this.alarmClock = alarmClock;
        this.coffeeMachine = coffeeMachine;
    }

    public void startMorningRoutine(){
        alarmClock.ring();

        if (alarmClock.wasRinging()){
            coffeeMachine.brewCoffee();
        }
    }
}
