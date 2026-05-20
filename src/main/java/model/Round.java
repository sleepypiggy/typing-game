package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import java.text.DecimalFormat;


// The Round class represents a single round in the typing game.
// A new Round is created each time the user wants to "play a new game",
// whether that is from the menu or at the end of a round. Each round has it's
// own information like the phrase the user must type, and the stats of the user
// the once the round is completed.

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
    private int numberOfUserTypedCharacters;

    
    // EFFECTS: initializes all things a round needs, and then runs the methods needed to 
    //          set the important details for the respective round.
    public Round(String phrasesPath, Random random, NanotimeClock clock) throws IOException {
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
    public void setNumberOfLines() throws IOException {
        reader = new BufferedReader(new FileReader(phrasesPath));
        while (reader.readLine() != null) {
            numberOfLines++;
        }
        reader.close();
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
    public void setActualText(int randomLineIndex) throws IOException {
        reader = new BufferedReader(new FileReader(phrasesPath));
        for (int i = 0; i < randomLineIndex; i++) {
            reader.readLine();
        }
        actualText = new StringBuilder(reader.readLine());
        reader.close();
    }

    // REQUIRES: actualText.length() > 0;
    // MODIFIES: this
    // EFFECTS: sets the number of characters the phrase of this round is based on the 
    //          random phrase selected.
    public void setNumberOfCharacters(StringBuilder actualText) {
        numberOfCharacters = actualText.length();
    }

    // REQUIRES: userText.length() > 0;
    // MODIFIES: this
    // EFFECTS: sets the number of characters the user typed this round.
    public void setNumberOfUserTypedCharacters(StringBuilder userText) {
        this.numberOfUserTypedCharacters = userText.length();
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

    // REQUIRES: numberOfCharacters > 0 && this.timeTaken > 0;
    // MODIFIES: this
    // EFFECTS: calculate the words types per minute by doing (numberOfCharacters /
    // 5) * (60 / timeTaken)
    public void setWordsPerMinute() {
        this.wordsPerMinute = (numberOfUserTypedCharacters / 5.0) * (60.0 / this.timeTaken);
        this.wordsPerMinute = Double.valueOf(decimalFormat.format(this.wordsPerMinute));
    }

        
    // MODIFIES: this
    // EFFECTS: sets this.userText to userText.
    public void setUserText(StringBuilder userText) {
        this.userText = userText;
    }

    // MODIFIES: this
    // EFFECTS: sets startTime to the current time in the system as determined by clock.
    public void startRoundTime() {
        this.startTime = clock.nanotimeClock();
    }

    // MODIFIES: this
    // EFFECTS: sets timeTaken to (clock.nanotimeClock() - startTime) / 1000000000.0, getting
    //          the amount of time between when start() is called and getElapsedTime() is called.
    public void setElapsedTime() {
        this.timeTaken = (clock.nanotimeClock() - startTime) / 1000000000.0;
        this.timeTaken = Double.valueOf(decimalFormat.format(this.timeTaken));
    }

    // MODIFIES: this
    // EFFECTS: resets the current round to a new one 
    public void newRound(int randomLineIndex) {
        try {
            setActualText(randomLineIndex);
        } catch (IOException ioe) {
            System.out.println("Something went wrong. ");
            ioe.printStackTrace();
        }
        this.wordsPerMinute = 0;
        this.accuracy = 0;
        this.timeTaken = 0;
        this.userText = null;
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
        return this.accuracy;
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

    public int getNumberOfUserTypedCharacters() {
        return numberOfUserTypedCharacters;
    }
}
