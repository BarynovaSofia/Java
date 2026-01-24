package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageFormatterTest {

    @Test
    void testFormatMessage() {
        MessageFormatter formatter = new MessageFormatter();

        List<String> messages = Arrays.asList(
                formatter.formatMessage("2025-05-14", "John Doe", "Hello, how are you?"),
                formatter.formatMessage("2025-05-13", "Jane Smith", "I need help with my project!"),
                formatter.formatMessage("2025-05-12", "Alex Brown", "Let's meet at 3 PM")
        );

        String regex = "\\d{4}-\\d{2}-\\d{2} - [A-Za-z ]+: .+";

        assertLinesMatch(
                Arrays.asList(regex, regex, regex),
                messages
        );
    }
}