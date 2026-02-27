package model;

import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestUserInfo {

    private String testPhrases = "data/testPhrases.txt";

    private UserInfo userInfo;

    private Round testRound1;
    private Round testRound2;
    private final int seed1 = 1;
    private final int seed2 = 2;

    

    @BeforeEach
    void setUp() throws IOException {
        testRound1 = new Round(testPhrases, new Random(seed1), new RoundTimer());
        testRound2 = new Round(testPhrases, new Random(seed2), new RoundTimer());
        userInfo = new UserInfo();
    }

    @Test
    void testAddSavedPhrase() {
        ArrayList<String> testList = new ArrayList<>();

        userInfo.addSavedPhrase(testRound1.getActualText());
        testList.add(testRound1.getActualText().toString());
        assertEquals(1, userInfo.getNumberOfSavedPhrases());
        assertEquals(testList, userInfo.getSavedPhrases());
    }

    @Test
    void testAddMultipleSavedPhrase() {
        ArrayList<String> testList = new ArrayList<>();

        userInfo.addSavedPhrase(testRound1.getActualText());
        testList.add(testRound1.getActualText().toString());
        assertEquals(1, userInfo.getNumberOfSavedPhrases());
        assertEquals(testList, userInfo.getSavedPhrases());

        userInfo.addSavedPhrase(testRound2.getActualText());
        testList.add(testRound2.getActualText().toString());
        assertEquals(2, userInfo.getNumberOfSavedPhrases());
        assertEquals(testList, userInfo.getSavedPhrases());
    }

    @Test
    void testAddSavedPhraseDuplicate() {
        ArrayList<String> testList = new ArrayList<>();

        userInfo.addSavedPhrase(testRound1.getActualText());
        testList.add(testRound1.getActualText().toString());
        assertEquals(1, userInfo.getNumberOfSavedPhrases());
        assertEquals(testList, userInfo.getSavedPhrases());

        userInfo.addSavedPhrase(testRound1.getActualText());
        assertEquals(1, userInfo.getNumberOfSavedPhrases());
        assertEquals(testList, userInfo.getSavedPhrases());
    }

    @Test
    void testAddPastRound() {
        ArrayList<Round> testList = new ArrayList<>();

        userInfo.addPastRound(testRound1);
        testList.add(testRound1);
        assertEquals(1, userInfo.getNumberOfPastRounds());
        assertEquals(testList, userInfo.getPastRounds());
    }

    @Test
    void testAddMultiplePastRound() {
        ArrayList<Round> testList = new ArrayList<>();

        userInfo.addPastRound(testRound1);
        testList.add(testRound1);
        assertEquals(1, userInfo.getNumberOfPastRounds());
        assertEquals(testList, userInfo.getPastRounds());

        userInfo.addPastRound(testRound2);
        testList.add(testRound2);
        assertEquals(2, userInfo.getNumberOfPastRounds());
        assertEquals(testList, userInfo.getPastRounds());
    }

    // rounds are rarely duplicate, and if they somehow are, I don't see
    // why there can't be duplicates.
    @Test
    void testAddPastRoundDuplicate() {
        ArrayList<Round> testList = new ArrayList<>();

        userInfo.addPastRound(testRound1);
        testList.add(testRound1);
        assertEquals(1, userInfo.getNumberOfPastRounds());
        assertEquals(testList, userInfo.getPastRounds());

        userInfo.addPastRound(testRound1);
        testList.add(testRound1);
        assertEquals(2, userInfo.getNumberOfPastRounds());
        assertEquals(testList, userInfo.getPastRounds());
    }

}
