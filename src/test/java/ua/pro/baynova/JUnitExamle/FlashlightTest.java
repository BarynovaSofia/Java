package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlashlightTest {
    Flashlight flashlight;

    @BeforeEach
    void setUp(){
        flashlight = new Flashlight();
    }

    @Test
    void testInitialTurnedOnCount(){
        assertEquals(0, flashlight.getTimesTurnedOn(), "В начале счетчик должен быть 0 в начале");
    }

    @Test
    void testTurnOnOnce(){
        flashlight.on();
        assertTrue(flashlight.isOn());
        assertEquals(1, flashlight.getTimesTurnedOn(), "Счетчик должен быть 1 после первого включения");
    }

    @Test
    void testTurnOnTwiceWithoutOff(){
        flashlight.on();
        flashlight.on();
        assertTrue(flashlight.isOn());
        assertEquals(1, flashlight.getTimesTurnedOn(), "Счетчик должен оставаться 1 после двойного on без off");
    }

    @Test
    void testTurnOnAfterOff(){
        flashlight.on();
        flashlight.off();
        flashlight.on();
        assertEquals(2, flashlight.getTimesTurnedOn(), "Счетчик должен быть 2 после двух включений");
    }

    @Test
    void testMultipleOnOffCycles(){
        flashlight.on();
        flashlight.off();
        flashlight.on();
        flashlight.off();
        flashlight.on();
        assertEquals(3, flashlight.getTimesTurnedOn(), "Счетчик должен быть 3 после 3 включений");
    }

//    @Test
//    void testInitiallyOff(){
//        assertFalse(flashlight.isOn(),"Фонарик должен быть выключен при создании");
//    }
//
//    @Test
//    void testTurnOn(){
//        flashlight.on();
//        assertTrue(flashlight.isOn(), "Фонарик должен быть включен после on()");
//    }
//
//    @Test
//    void testTurnOffAfterOn(){
//        flashlight.on();
//        flashlight.off();
//        assertFalse(flashlight.isOn(), "Фонарик должен быть выключен после off()");
//    }
//
//    @Test
//    void testMultipleOnCalls(){
//        flashlight.on();
//        flashlight.on();
//        assertTrue(flashlight.isOn(), "Многократный on() должен оставить фонарик включенным");
//    }
//
//    @Test
//    void testMultipleOffCalls(){
//        flashlight.off();
//        flashlight.off();
//        assertFalse(flashlight.isOn(), "Многократный off() должен оставить фонарик выключенным");
//    }

}