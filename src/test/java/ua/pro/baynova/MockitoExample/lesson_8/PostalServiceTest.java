package ua.pro.baynova.MockitoExample.lesson_8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class PostalServiceTest {

    @Spy
    PostalService postalService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendLetterWithMockedValidation(){
        doReturn(true).when(postalService).validateAddress("ул. Примерная, 10");

        postalService.sendLetter("ул. Примерная, 10", "Добрый день!");

        verify(postalService).sendLetter("ул. Примерная, 10", "Добрый день!");
    }
}