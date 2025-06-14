package ua.pro.baynova.TDD_Example.lesson_3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DuplicateRemoverTest {

    @Test
    void testRemoveDuplicates_basic(){
        List<String> input = List.of("Java", "Python", "Java", "C++");
        List<String> expected = List.of("Java", "Python", "C++");

        List<String> result = DuplicateRemover.removeDuplicates(input);
        assertEquals(expected, result);
    }

    @Test
    void testRemoveDuplicates_caseInsensitive(){
        List<String> input = List.of("Java", "java", "JAVA", "Python");
        List<String> expected = List.of("Java", "Python");

        List<String> result = DuplicateRemover.removeDuplicates(input);
        assertEquals(expected, result);
    }

    @Test
    void testRemoveDuplicates_emptyList(){
        List<String> input = List.of();
        List<String> expected = List.of();

        List<String> result = DuplicateRemover.removeDuplicates(input);
        assertEquals(expected, result);
    }

    @Test
    void testRemoveDuplicates_noDuplicates(){
        List<String> input = List.of("Go", "Kotlin", "Rust");
        List<String> expected = List.of("Go", "Kotlin", "Rust");

        List<String> result = DuplicateRemover.removeDuplicates(input);
        assertEquals(expected, result);
    }

    @Test
    void testRemoveDuplicates_allDuplicates(){
        List<String> input = List.of("JS", "js", "Js", "jS");
        List<String> expected = List.of("JS");

        List<String> result = DuplicateRemover.removeDuplicates(input);
        assertEquals(expected, result);
    }
}
