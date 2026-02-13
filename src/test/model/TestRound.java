package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class TestRound {
    private final int seed = 1;
    private String testPhrases = "data/testPhrases.txt";
    private Round testRound;
    
    @BeforeEach
    void setUp() {
        testRound = new Round(testPhrases, new Random(seed), new RoundTimer());
    }

    @Test
    void testSetNumberOfLines() {
        assertEquals(10, testRound.getNumberOfLines());
    }

    @Test
    void testSetRandomLineIndex() {
        // A seeded random will return the same pattern of random numbers every time, at least
        // in this scenario tested.
        // In this case, with a seed of 1, it will return 5 every single time the test is called.
        assertEquals(5, testRound.getRandomLineIndex());
    }

    @Test
    void testSetActualText() {
        // assertEquals keeps reference based equality testing for StringBuilder
        // so we have to convert to String.
        assertEquals("Index 5", testRound.getActualText().toString());
    }

    @Test
    void testSetNumberOfCharacters() {
        assertEquals(7, testRound.getNumberOfCharacters());
    }

    @Test
    void testCalculateAccuracy100Percent() {
        testRound.setUserText(new StringBuilder("Index 5"));
        testRound.calculateAccuracy(testRound.getActualText(), testRound.getUserText());
        assertEquals(100.00, testRound.getAccuracy());
    }

    @Test
    void testCalculateAccuracy0Percent() {
        testRound.setUserText(new StringBuilder("abcdefg"));
        testRound.calculateAccuracy(testRound.getActualText(), testRound.getUserText());
        assertEquals(0.00, testRound.getAccuracy());
    }
    
    @Test
    void testCalculateAccuracyRandomPercent() {
        testRound.setUserText(new StringBuilder("Inaaa 4"));
        testRound.calculateAccuracy(testRound.getActualText(), testRound.getUserText());
        assertEquals(42.86, testRound.getAccuracy());
    }

    // this test depends on time so I'll fill it in later
    @Test
    void testCalculateWordsPerMinute() {
        
    }
}
