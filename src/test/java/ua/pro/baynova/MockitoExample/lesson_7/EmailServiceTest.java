package ua.pro.baynova.MockitoExample.lesson_7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class EmailServiceTest {

    @Mock
    EmailClient emailClient;

    @InjectMocks
    EmailService emailService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNotifyClient_WhenEmailSendFails_ShouldReturnFalse(){
        doThrow(new RuntimeException("Сервер недоступен"))
                .when(emailClient)
                .sendEmail("test@example.com", "Привет!");

        boolean result = emailService.notifyClient("test@example.com", "Привет!");

        assertFalse(result);

        verify(emailClient).sendEmail("test@example.com", "Привет!");
    }
}