package ua.pro.baynova.MockitoExample.lesson_9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PizzaOrderTest {

    @Mock
    PizzaService pizzaService;

    @InjectMocks
    PizzaOrder pizzaOrder;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPizzaBakingExactlyThreeTimes(){

        pizzaOrder.makeOrder(3);

        verify(pizzaService, times(3)).bakePizza();
    }
}