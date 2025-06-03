package ua.pro.baynova.MockitoExample.lesson_10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class AntiCheatSystemTest {

    @Mock
    CheatDetector cheatDetector;

    @Mock
    BanService banService;

    @Mock
    NotificationService notificationService;

    @InjectMocks
    AntiCheatSystem antiCheatSystem;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheaterGetsBannedAndNotified(){
        String playerId = "cheater123";

        doReturn(true).when(cheatDetector).hasCheats(playerId);

        antiCheatSystem.checkPlayer(playerId);

        verify(banService).ban(playerId);
        verify(notificationService).notify(playerId, "Вы забанены за использование читов.");

        verifyNoMoreInteractions(banService, notificationService);
    }

    @Test
    void testHonestPlayerGetsWelcome(){
        String playerId = "honestPlayer";

        doReturn(false).when(cheatDetector).hasCheats(playerId);

        antiCheatSystem.checkPlayer(playerId);

        verifyNoInteractions(banService);
        verify(notificationService).notify(playerId, "Добро пожаловать в игру!");

        verifyNoMoreInteractions(notificationService);
    }

    @Test
    void testCheckFailureSendsErrorNotification(){
        String playerId = "errorCase";

        doThrow(new RuntimeException("Ошибка проверки")).when(cheatDetector).hasCheats(playerId);

        antiCheatSystem.checkPlayer(playerId);

        verifyNoInteractions(banService);
        verify(notificationService).notify(playerId, "Не удалось проверить ваш аккаунт. Повторите попытку позже.");

        verifyNoMoreInteractions(notificationService);
    }
}
