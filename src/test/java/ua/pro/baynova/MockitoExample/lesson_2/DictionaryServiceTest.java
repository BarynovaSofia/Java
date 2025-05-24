package ua.pro.baynova.MockitoExample.lesson_2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DictionaryServiceTest {

    @Test
    public void testTranslationWithMockedTranslator(){
        Translator mockTranslator = mock(Translator.class);

        when(mockTranslator.translate(anyString())).thenAnswer(invocation -> {
            String word = invocation.getArgument(0);

            switch (word) {
                case "cat": return "кот";
                case "dog": return "собака";
                case "bird": return "птица";
                default: return "неизвестно";
            }
        });

        DictionaryService dictionary = new DictionaryService(mockTranslator);

        assertEquals("кот", dictionary.translateWord("cat"));
        assertEquals("собака", dictionary.translateWord("dog"));
        assertEquals("птица", dictionary.translateWord("bird"));
        assertEquals("неизвестно", dictionary.translateWord("tree"));
    }

}