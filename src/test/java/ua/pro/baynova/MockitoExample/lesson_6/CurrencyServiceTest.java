package ua.pro.baynova.MockitoExample.lesson_6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class CurrencyServiceTest {

    @Mock
    CurrencyApi currencyApi;

    @InjectMocks
    CurrencyService currencyService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertEurToUsd(){
        doReturn(1.1).when(currencyApi).getExchangeRate("EUR", "USD");

        double result = currencyService.convertEurToUsd(50);

        assertEquals(55.0, result, 0.001);

        verify(currencyApi).getExchangeRate("EUR", "USD");
    }

}