package ua.pro.baynova.TDD_Example.lesson_2;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class WordFilterTest {

    @Test
    void testFilterWords_basic(){
        String sentence = "Java and JavaScript are different";
        List<String> result = WordFilter.filterWords(sentence, 4, 'j');
        assertEquals(List.of("Java", "JavaScript"), result);
    }

    @Test
    void testFilterWords_second(){
        String sentence = "some small silly string";
        List<String> result = WordFilter.filterWords(sentence, 5, 's');
        assertEquals(List.of("small", "silly", "string") ,result);
    }

    @Test
    void testFilterWords_emptySentence(){
        assertEquals(List.of(), WordFilter.filterWords("", 3, 'a'));
    }

    @Test
    void testFilterWords_noMatch(){
        assertEquals(List.of(), WordFilter.filterWords("hello world", 10, 'z'));
    }

    @Test
    void testFilterWords_caseInsensitive(){
        String sentence = "Dog deer door Dive duck";
        List<String> result = WordFilter.filterWords(sentence, 3, 'd');
        assertEquals(List.of("Dog", "deer", "door", "Dive", "duck"), result);
    }
}
