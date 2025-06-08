package ua.pro.baynova.MockitoExample.lesson_11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class BugReportServiceTest {

    @Mock
    ModerationService moderationService;

    @Mock
    LoggerService loggerService;

    @InjectMocks
    BugReportService bugReportService;

    @Captor
    ArgumentCaptor<BugReport> reportCaptor;

    @Captor
    ArgumentCaptor<String> logCaptor;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBugReportSubmissionAndLogging(){
        String playerId = "player007";
        String bugDescription = "Игрок застрял в текстурах на карте X.";

        bugReportService.reportBug(playerId, bugDescription);

        verify(moderationService).submitReport(reportCaptor.capture());
        BugReport captured = reportCaptor.getValue();

        assertEquals("player007", captured.getPlayerId());
        assertEquals("Игрок застрял в текстурах на карте X.", captured.getDescription());

        verify(loggerService).log(logCaptor.capture());
        String logMessage = logCaptor.getValue();

        assertEquals("Bug report from player007: Игрок застрял в текстурах на карте X.", logMessage);
    }
}
