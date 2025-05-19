package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NicknameGeneratorTest {
    NicknameGenerator generator = new NicknameGenerator();

    @Test
    void testBaseNameGeneration(){
        String result = generator.generateBaseName("Anna");
        assertEquals("Anna_user", result);
    }

    @Test
    void testNameWithNumber(){
        String result = generator.generateNameWithNumber("Alex");
        assertTrue(result.startsWith("Alex"));
        assertTrue(result.length() > 4);
    }

    @Disabled("Эмодзи ещё не реализованы — ждём дизайнера 🤷‍♀️")
    @Test
    void testNameWithEmoji(){
        String result = generator.generateNameWithEmoji("Sasha");
        assertTrue(result.contains("🌟") || result.contains("🔥"));
    }
}