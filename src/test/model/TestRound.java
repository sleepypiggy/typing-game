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
        testRound = new Round(testPhrases, new Random(seed));
    }

    @Test
    void testSetNumberOfLines() {
        assertEquals(10, testRound.getNumberOfLines());
    }

    @Test
    void testSetRandomLineIndex() {
        // A seeded random will return the same pattern of random numbers every time, at least
        // in this scenario tested.
        // In this case, with a seed of 5, it will return 5 every single time the test is called.
        assertEquals(5, testRound.getRandomLineIndex());
    }

    @Test
    void testSetActualText() {
        assertEquals("Index 5", testRound.getActualText());
    }

    @Test
    void testSetNumberOfCharacters() {
        assertEquals(7, testRound.getNumberOfCharacters());
    }

    @Test
    void testCalculateAccuracy100Percent() {
        testRound.setUserText("Index 5");
        assertEquals(100.00, testRound.getAccuracy());
    }

    @Test
    void testCalculateAccuracy0Percent() {
        testRound.setUserText("abcdefg");
        assertEquals(0.00, testRound.getAccuracy());
    }

    // this test depends on time so I'll fill it in later
    @Test
    void testCalculateWordsPerMinute() {
        
    }  
}
