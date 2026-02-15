package ui;

import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

import model.Round;
import model.RoundTimer;
import model.UserInfo;

// The TypingGame class represents the core structure of the typing game.
// It is where the structure of the game itself is being managed, including the 
// main UI elements. This is the class that gets input from the user, like if they want
// to start a game, quit, or save a phrase.

public class TypingGame {

    private Scanner scanner;
    private Random random;
    private Round round;
    private RoundTimer roundTimer;
    private UserInfo userInfo;
    private boolean gameRunning;
    private String userChoice;
    private String afterRoundUserChoice;

    private String phrasesPath = "data/phrases.txt";

    public TypingGame() {
        userInfo = new UserInfo();
        scanner = new Scanner(System.in);
        random = new Random();
        roundTimer = new RoundTimer();
        gameRunning = true;
        mainGameLoop();
    }

    // MODIFIES: this
    // EFFECTS: structures the main game loop of each section of the game.
    public void mainGameLoop() {
        
    }

    // EFFECTS: adds the current round's text to savedPhrases.
    public void savePhrase() {
        
    }

    // EFFECTS: prints all the user's saved phrases. Prints "No saved phrases. " if there are none.
    public void viewSavedPhrases() {
        
    }

    // EFFECTS: creates a new round.
    public void newRound() {
        
    }

    // EFFECTS: starts the main game loop or allows the user to exit the program.
    public void playRound() {
        
    }

    // EFFECTS: displays the stats for the current round after it is over.
    public void afterRoundStats(StringBuilder userInput) {
        
    }

    // EFFECTS: displays the options the user has in the console after the current round is over.
    public void printAfterRoundOptions() {
        
    }

    // EFFECTS: displays the options the user has in the console at the beginning of the game.
    public void printStartGameOptions() {
        
    }

    // REQUIRES: duration > 0;
    // EFFECTS: creates a countdown of duration seconds. Used to give the player a few
    //          seconds to read the phrase they have to type.
    public void countdown(int duration) {
        
    }

}
