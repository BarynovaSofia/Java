package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CounterServiceTest {
    CounterService counter;

    @BeforeEach
    void setUp(){
        counter = new CounterService();
        System.out.println("~ BeforeEach: создаём новый экземпляр CounterService");
    }

    @AfterEach
    void tearDown(){
        System.out.println("~ AfterEach: тест завершён, можно освободить ресурсы");
    }

    @Test
    void testInitialCountIsZero(){
        assertEquals(0, counter.getCount());
    }

    @Test
    void testIncrementOnce(){
        counter.increment();
        assertEquals(1, counter.getCount());
    }

    @Test
    void testIncrementTwice(){
        counter.increment();
        counter.increment();
        assertEquals(2, counter.getCount());
    }



}