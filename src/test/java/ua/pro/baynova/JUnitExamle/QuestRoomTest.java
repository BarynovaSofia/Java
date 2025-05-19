package ua.pro.baynova.JUnitExamle;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestRoomTest {
    QuestRoom room = new QuestRoom();

    @Tag("logic")
    @Test
    void testLogicPuzzleCorrectAnswer(){
        assertTrue(room.solveLogicPuzzle("42"));
    }

    @Tag("logic")
    @Test
    void testLogicPuzzleWrongAnswer(){
        assertFalse(room.solveLogicPuzzle("hello"));
    }

    @Tag("physical")
    @Test
    void testPhysicalTestEnoughPushUps(){
        assertTrue(room.passPhysicalTest(15));
    }

    @Tag("physical")
    @Test
    void testPhysicalTestNotEnoughPushUps(){
        assertFalse(room.passPhysicalTest(5));
    }

    @Tag("mystic")
    @Test
    void testMysticRiddleCorrect(){
        assertTrue(room.solveMysticRiddle("Она исчезла, как тень..."));
    }

    @Tag("mystic")
    @Test
    void testMysticRiddleIncorrect(){
        assertFalse(room.solveMysticRiddle("Свет побеждает"));
    }
}