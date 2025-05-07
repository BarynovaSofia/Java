package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeServiceTest {

    GradeService service = new GradeService();

    @Test
    void testGradeA() {
        String result = service.getLetterGrade(95);
        assertEquals("A" , result);
    }

    @Test
    void testGradeB(){
        String result = service.getLetterGrade(85);
        assertEquals("B", result);
    }

    @Test
    void testGradeC(){
        String result = service.getLetterGrade(75);
        assertEquals("C", result);
    }

    @Test
    void testGradeD(){
        String result = service.getLetterGrade(65);
        assertEquals("D", result);
    }
    @Test
    void testGradeF(){
        String result = service.getLetterGrade(42);
        assertEquals("F", result);
    }

    @Test
    void testGradeA_Border(){
        assertEquals("A", service.getLetterGrade(90));
    }

    @Test
    void testGradeB_Border(){
        assertEquals("B", service.getLetterGrade(89));
        assertEquals("B", service.getLetterGrade(80));
    }

    @Test
    void testGradeC_Border(){
        assertEquals("C", service.getLetterGrade(79));
        assertEquals("C", service.getLetterGrade(70));
    }

    @Test
    void testGradeD_Border(){
        assertEquals("D", service.getLetterGrade(69));
        assertEquals("D", service.getLetterGrade(60));
    }

    @Test
    void testTooHighScoreThrowsException(){
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            service.getLetterGrade(150);
        });
        assertEquals("Оценка должна быть от 0 до 100", ex.getMessage());
    }

    @Test
    void testNegativeScoreThrowsException(){
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            service.getLetterGrade(-5);
        });
        assertEquals("Оценка должна быть от 0 до 100", ex.getMessage() );
    }
}