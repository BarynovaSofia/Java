package ua.pro.baynova.MockitoExample.lesson_5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class EmailServiceTest {

    @Mock
    MailSender mailSender;

    @InjectMocks
    EmailService emailService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendWelcomeEmail_TrimsEmailAndSendsCorrectMessage(){

        emailService.sendWelcomeEmail("   user@example.com   ");

        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        verify(mailSender).send(emailCaptor.capture(), messageCaptor.capture());

        String actualEmail = emailCaptor.getValue();
        String actualMessage = messageCaptor.getValue();

        assertEquals("user@example.com", actualEmail);
        assertEquals("Welcome to our platform!", actualMessage);
    }
}