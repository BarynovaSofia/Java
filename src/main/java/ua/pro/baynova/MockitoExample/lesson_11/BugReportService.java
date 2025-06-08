package ua.pro.baynova.MockitoExample.lesson_11;

public class BugReportService {
    private final ModerationService moderationService;
    private final LoggerService loggerService;

    public BugReportService(ModerationService moderationService, LoggerService loggerService){
        this.moderationService = moderationService;
        this.loggerService = loggerService;
    }

    public void reportBug(String playerId, String description){
        BugReport report = new BugReport(playerId, description);
        moderationService.submitReport(report);
        loggerService.log("Bug report from " + playerId + ": " + description);
    }
}
