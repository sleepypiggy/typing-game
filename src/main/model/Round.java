package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.text.DecimalFormat;


// The Round class represents a single round in in the typing game.
// A new Round is created each time the user wants to "play a new game",
// whether that is from the menu or at the end of a round.

public class Round {
    private final Random random;
    private BufferedReader reader;
    private DecimalFormat decimalFormat;
    private String phrasesPath;
    private NanotimeClock clock;
    private double startTime;

    private StringBuilder userText;
    private StringBuilder actualText;
    private int numberOfLines = 0;
    private int randomLineIndex;

    // round stats
    private double wordsPerMinute;
    private double accuracy;
    private double timeTaken;
    private int numberOfCharacters;

    public Round(String phrasesPath, Random random, NanotimeClock clock) {
        this.random = random;
        this.phrasesPath = phrasesPath;
        this.clock = clock;
        decimalFormat = new DecimalFormat("#.##");
        setNumberOfLines();
        setRandomLineIndex(numberOfLines);
        setActualText(randomLineIndex);
        setNumberOfCharacters(actualText);
    }

    // REQUIRES: the number of lines of the text file in the location phrasesPath > 0.
    // MODIFIES: this
    // EFFECTS: sets the number of lines in text file to be used for random
    //          selection
    public void setNumberOfLines() {
        try {
            reader = new BufferedReader(new FileReader(phrasesPath));
            while (reader.readLine() != null) {
                numberOfLines++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found. ");
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: generates a random number from 0 to but not including numberOfLines
    public void setRandomLineIndex(int numberOfLines) {
        randomLineIndex = random.nextInt(numberOfLines);
    }

    // REQUIRES: randomLineIndex >= 0 && randomLineIndex <= numberOfLines - 1
    // MODIFIES: this
    // EFFECTS: using the randomly generated index, selects the corresponding
    //          text from the file and sets it as actualText
    public void setActualText(int randomLineIndex) {
        try {
            reader = new BufferedReader(new FileReader(phrasesPath));
            for (int i = 0; i < randomLineIndex; i++) {
                reader.readLine();
            }
            actualText = new StringBuilder(reader.readLine());
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found. ");
            e.printStackTrace();
        }
    }

    // REQUIRES: actualText.length() > 0;
    // MODIFIES: this
    // EFFECTS: sets the number of characters the phrase of this round is based on the 
    //          random phrase selected.
    public void setNumberOfCharacters(StringBuilder actualText) {
        numberOfCharacters = actualText.length();
    }

    // REQURIES: actualText.length() > 0;
    // MODIFIES: this
    // EFFECTS: calculates the accuracy when comparing the user's typed phrase
    //          versus the actual phrase and sets it as accuracy.
    public void calculateAccuracy(StringBuilder actualText, StringBuilder userText) {
        double wrong = 0.0;

        while (userText.length() < actualText.length()) {
            userText.append("⨘");
        }

        while (userText.length() > actualText.length()) {
            actualText.append("⨘");
        }

        for (int i = 0; i < actualText.length(); i++) {
            if (userText.charAt(i) != actualText.charAt(i)) {
                wrong++;
            }
        }
        double percentageWrong = wrong / actualText.length() * 100.0;
        double accuracy = 100.0 - percentageWrong;
        this.accuracy = Double.valueOf(decimalFormat.format(accuracy));
    }

    // MODIFIES: this
    // EFFECTS: sets startTime to the current time when the round starts.
    public void startRound() {

    }

    // MODIFIES: this
    // EFFECTS: sets the amount of time taken for the user to complete the round by
    //          keeping track of the startTime and then setTimeTaken() is called.
    public void setTimeTaken() {

    }

    // REQUIRES: numberOfCharacters > 0;
    // MODIFIES: this
    // EFFECTS: calculate the words types per minute by doing (numberOfCharacters /
    // 5) * (60 / timeTaken)
    public void calculateWordsPerMinute() {

    }

    public StringBuilder getActualText() {
        return this.actualText;
    }

    public StringBuilder getUserText() {
        return userText;
    }

    public double getWordsPerMinute() {
        return wordsPerMinute;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getTimeTaken() {
        return timeTaken;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public int getRandomLineIndex() {
        return randomLineIndex;
    }

    public int getNumberOfCharacters() {
        return numberOfCharacters;
    }
    
    // MODIFIES: this
    // EFFECTS: sets this.userText to userText.
    public void setUserText(StringBuilder userText) {
        this.userText = userText;
    }

    // MODIFIES: this
    // EFFECTS: sets startTime to the current time in the system as determined by clock.
    public void start() {
        startTime = clock.nanotimeClock();
    }

    // MODIFIES: this
    // EFFECTS: sets timeTaken to (clock.nanotimeClock() - startTime) / 1000000000.0, getting
    //          the amount of time between when start() is called and getElapsedTime() is called.
    public void getElapsedTime() {
        this.timeTaken = (clock.nanotimeClock() - startTime) / 1000000000.0;
        this.timeTaken = Double.valueOf(decimalFormat.format(this.timeTaken));
    }
}
