package model;

import java.util.Random;

// The Round class represents a single round in in the typing game.
// A new Round is created each time the user wants to "play a new game",
// whether that is from the menu or at the end of a round.

public class Round {
    private String phrasesPath;
    private long startTime;
    private long endTime;

    private String userText;
    private String actualText;
    private int numberOfLines = 0;
    private int randomLineIndex;

    // round stats
    private double wordsPerMinute;
    private double accuracy;
    private double timeTaken;
    private int numberOfCharacters;

    public Round(String phrasesPath, Random random) {

    }

    // REQUIRES: the number of lines of the text file in the location phrasesPath > 0.
    // MODIFIES: this
    // EFFECTS: sets the number of lines in text file to be used for random
    //          selection
    public void setNumberOfLines() {

    }

    // REQUIRES: numberOfLines > 0
    // MODIFIES: this
    // EFFECTS: generates a random number from 0 to but not including numberOfLines
    public void setRandomLineIndex(int numberOfLines) {

    }

    // REQUIRES: randomLineIndex >= 0 && randomLineIndex <= numberOfLines - 1
    // MODIFIES: this
    // EFFECTS: using the randomly generated index, selects the corresponding
    //          text from the file and sets it as actualText
    public void setActualText(int randomLineIndex) {

    }

    // REQUIRES: actualText.length() > 0;
    // MODIFIES: this
    // EFFECTS: sets the number of characters the phrase of this round is based on the 
    //          random phrase selected.
    public void setNumberOfCharacters(String actualText) {

    }

    // REQURIES: actualText.length() > 0;
    // MODIFIES: this
    // EFFECTS: calculates the accuracy when comparing the user's typed phrase
    //          versus the actual phrase and sets it as accuracy.
    public void calculateAccuracy(String actualText, StringBuilder userText) {

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

    public String getActualText() {
        return ""; //stub
    }

    public String getUserText() {
        return ""; //stub
    }

    public double getWordsPerMinute() {
        return 0.0; //stub
    }

    public double getAccuracy() {
        return 0.0; //stub
    }

    public double getTimeTaken() {
        return 0.0; //stub
    }

    public int getNumberOfLines() {
        return 0; //stub
    }

    public int getRandomLineIndex() {
        return 0; //stub
    }

    public int getNumberOfCharacters() {
        return 0; //stub
    }
    
    // for testing purposes
    // MODIFIES: this
    // EFFECTS: sets this.userText to userText. Only used for testing purposes.
    public void setUserText(String userText) {

    }
}
