package ua.pro.baynova.MockitoExample.lesson_9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class StoreServiceTest {

    @Mock
    PaymentGateway gateway;

    @InjectMocks
    StoreService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPaymentTriedAtLeastTwoTimes(){
        doReturn(false).when(gateway).charge("1234", 100.0);

        service.processPayment("1234", 100.0);

        verify(gateway, atLeast(2)).charge("1234", 100.0);
    }

    @Test
    void testChargeCalledAtMostThreeTimes(){
        doReturn(false).when(gateway).charge("1234", 100.0);

        service.processPayment("1234", 100.0);

        verify(gateway, atMost(3)).charge("1234", 100.0);
    }
}
