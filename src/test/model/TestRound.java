package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

public class TestRound {
    private final int seed = 1;
    private String testPhrases = "data/testPhrases.txt";
    private Round testRound;
    private Round testTimerRound;
    private FakeNanotimeClock fakeClock;
    
    @BeforeEach
    void setUp() {
        fakeClock = new FakeNanotimeClock();
        try {
            testRound = new Round(testPhrases, new Random(seed), new RoundTimer());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            testTimerRound = new Round(testPhrases, new Random(seed), fakeClock);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Test
    void testCalculateAccuracyUserTextLongerThanActualText() {
        testRound.setUserText(new StringBuilder("Index 5aaa"));
        testRound.calculateAccuracy(testRound.getActualText(), testRound.getUserText());
        assertEquals(70.00, testRound.getAccuracy());
    }

    @Test
    void testCalculateAccuracyActualTextLongerThanUserText() {
        testRound.setUserText(new StringBuilder("Index"));
        testRound.calculateAccuracy(testRound.getActualText(), testRound.getUserText());
        assertEquals(71.43, testRound.getAccuracy());
    }

    // this test depends on time so I'll fill it in later
    @Test
    void testCalculateWordsPerMinute() {
        fakeClock.setTime(1000000000);
        testTimerRound.startRoundTime();
        fakeClock.setTime(2100000000); // 1.1 seconds
        testTimerRound.setElapsedTime();
        testTimerRound.setUserText(new StringBuilder("1234567"));
        assertEquals(7, testTimerRound.getUserText().length());
        testTimerRound.setNumberOfUserTypedCharacters(testTimerRound.getUserText());
        assertEquals(7, testTimerRound.getNumberOfUserTypedCharacters());
        testTimerRound.setWordsPerMinute();

        assertEquals(1.1, testTimerRound.getTimeTaken());
        assertEquals(76.36, testTimerRound.getWordsPerMinute());
    }

    @Test 
    void testGetElapsedTime() {
        fakeClock.setTime(1000000000);
        testTimerRound.startRoundTime();
        fakeClock.setTime(1500000000);
        // time is set in nanoseconds but getElapsedTime() returns it in seconds

        testTimerRound.setElapsedTime();
        assertEquals(0.5, testTimerRound.getTimeTaken());
    }

    @Test
    void testFileNotFound() {
        assertThrows(IOException.class, () ->  {
            new Round("data/phrase.txt", new Random(seed), fakeClock);
        });
    }
}
