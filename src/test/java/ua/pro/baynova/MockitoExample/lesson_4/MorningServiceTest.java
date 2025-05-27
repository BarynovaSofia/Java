package ua.pro.baynova.MockitoExample.lesson_4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class MorningServiceTest {

    @Mock
    AlarmClock alarmClock;

    @Mock
    CoffeeMachine coffeeMachine;

    @InjectMocks
    MorningService morningService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMorningRoutine(){

        when(alarmClock.wasRinging()).thenReturn(true);

        morningService.startMorningRoutine();

        verify(alarmClock).ring();
        verify(alarmClock).wasRinging();
        verify(coffeeMachine).brewCoffee();
    }

    @Test
    void testMorningRoutine_AlarmFailed(){
        when(alarmClock.wasRinging()).thenReturn(false);

        morningService.startMorningRoutine();

        verify(alarmClock).ring();
        verify(alarmClock).wasRinging();
        verify(coffeeMachine, never()).brewCoffee();
    }
}