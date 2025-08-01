package ua.pro.baynova.ThreadsExample.lesson_15.Loggers.part_4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String format(LogRecord record) {
        String timestamp = sdf.format(new Date(record.getMillis()));
        String thread = Thread.currentThread().getName();
        String level = record.getLevel().getName();
        String message = formatMessage(record);

        return String.format("[%s] [%s] [%s] %s%n", timestamp, thread, level, message);
    }
}
