package ua.pro.baynova.MockitoExample.lesson_7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class ATMTest {

    @Mock
    BankService bankService;

    @InjectMocks
    ATM atm;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testWithdrawal_CardBlocked(){
        doThrow( new RuntimeException("Карта заблокирована"))
                .when(bankService).withdraw("1234-5678-9999", 100);

        String result = atm.makeWithdrawal("1234-5678-9999", 100);

        assertEquals("Ошибка: Карта заблокирована", result);

        verify(bankService).withdraw("1234-5678-9999" , 100);
    }
}