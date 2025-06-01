package ua.pro.baynova.MockitoExample.lesson_8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class NotificationServiceTest {

    @Spy
    NotificationService notificationService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendAllWithStubbedSms(){
        doNothing().when(notificationService).sendSms(anyString(), anyString());

        notificationService.sendAll("test@email.com", "1234567890", "Привет!");

        verify(notificationService).sendEmail("test@email.com", "Привет!");
        verify(notificationService).sendSms("1234567890", "Привет!");
    }
}