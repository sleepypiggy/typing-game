package ui;

// TODO: - add ways to remove phrases from favorites
// TODO: - let user add custom quotes to .txt file.

import java.util.Scanner;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import persistence.JsonReader;
import persistence.JsonWriter;

import model.Round;
import model.RoundTimer;
import model.UserInfo;

// The TypingGame class represents the core structure of the typing game.
// It is where the structure of the game itself is being managed, including the 
// main UI elements. This is the class that gets input from the user, like if they want
// to start a game, quit, or save a phrase.

@ExcludeFromJacocoGeneratedReport
public class TypingGame {
    private static final String JSON_STORE = "./data/userInfo.json";

    private Scanner scanner;
    private Random random;
    private Round round;
    private RoundTimer roundTimer;
    private UserInfo userInfo;
    private boolean gameRunning;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private GameWindow gameWindow;

    // technically these two don't need to be fields
    private String userChoice;
    private String afterRoundUserChoice;

    private boolean isFirstRound;

    private String phrasesPath = "data/phrases.txt";

    // EFFECTS: initializes all required objects for the game to run and store information, and starts
    //          the main game loop.
    public TypingGame() {
        userInfo = new UserInfo();
        scanner = new Scanner(System.in);
        random = new Random();
        roundTimer = new RoundTimer();
        gameRunning = true;
        isFirstRound = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        mainGameLoop();
    }

    // MODIFIES: this
    // EFFECTS: structures the main game loop of each section of the game.
    @SuppressWarnings("methodlength")
    // I don't think I can condense this method anymore, let alone by 7 more lines.
    public void mainGameLoop() {
        while (gameRunning) {
            newRound();
            printStartGameOptions();
            userChoice = scanner.nextLine();
            System.out.println();
            // Using a switch statement makes it easier to read but makes the logic more complicated.
            if (userChoice.equalsIgnoreCase("S")) {
                playRound();
                while (true) {
                    printAfterRoundOptions();
                    afterRoundUserChoice = scanner.nextLine();
                    System.out.println();
                    if (afterRoundUserChoice.equalsIgnoreCase("P")) {
                        break;
                    } else if (afterRoundUserChoice.equalsIgnoreCase("S")) {
                        savePhrase();
                    } else if (afterRoundUserChoice.equalsIgnoreCase("V")) {
                        viewSavedPhrases();
                    } else if (afterRoundUserChoice.equalsIgnoreCase("R")) {
                        removePhrase();
                    } else if (afterRoundUserChoice.equalsIgnoreCase("D")) {
                        saveUserInfo();
                    } else if (afterRoundUserChoice.equalsIgnoreCase("L")) {
                        loadUserInfo();
                    } else if (afterRoundUserChoice.equalsIgnoreCase("E")) {
                        System.out.println("Goodbye. ");
                        gameRunning = false;
                        break;
                    } else {
                        System.out.println("Invalid input. Try again. ");
                    }
                }
            } else if (userChoice.equalsIgnoreCase("E")) {
                System.out.println("Goodbye. ");
                break;
            } else {
                System.out.println("That was not a valid input. Try again. ");
            }
        }
    }

    // MODIFIES: userInfo
    // EFFECTS: adds the current round's text to savedPhrases.
    public void savePhrase() {
        if (!userInfo.getSavedPhrases().contains(round.getActualText().toString())) {
            userInfo.addSavedPhrase(round.getActualText());
            System.out.println("Phrase saved! ");
        } else {
            System.out.println("You already saved this phrase. ");
        }
    }

    // !!! THIS DOESN'T WORK PROPERLY SINCE IT AWAITS USER INPUT FROM THE CONSOEL BRUH
    // MODIFIES: userInfo
    // EFFECTS: removes the corresponding phrase from given phrase number from savedPhrases.
    public void removePhrase() {
        if (userInfo.getSavedPhrases().isEmpty()) {
            System.out.println("There are no phrases to remove. ");
        } else {
            System.out.print("Which phrase number would you like to remove?: ");
            int phraseNumber = scanner.nextInt();
            if (phraseNumber < 1 || phraseNumber > userInfo.getNumberOfSavedPhrases()) {
                System.out.println("This phrase number does not exist. ");
                scanner.nextLine();
            } else {
                userInfo.removeSavedPhrase(phraseNumber);
                System.out.println("Phrase removed! ");
                scanner.nextLine();
            }
        } 
    }

    // EFFECTS: prints all the user's saved phrases. Prints "No saved phrases. " if there are none.
    public void viewSavedPhrases() {
        if (userInfo.getSavedPhrases().isEmpty()) {
            System.out.println("No saved phrases. ");
        } else {
            System.out.println("Saved phrases: ");
            System.out.println("-------------- ");
            for (int i = 0; i <= userInfo.getNumberOfSavedPhrases() - 1; i++) {
                System.out.println("- " + userInfo.getSavedPhrases().get(i));
            }
            System.out.println();
        }
    }

    // EFFECTS: creates a new round.
    public void newRound() {
        if (isFirstRound) {
            try {
                round = new Round(phrasesPath, random, roundTimer);
                gameWindow = new GameWindow(this, round, userInfo);
                isFirstRound = false;
            } catch (IOException e) {
                System.out.println("File not found. ");
                e.printStackTrace();
            }
        } else {
            try {
                round = new Round(phrasesPath, random, roundTimer);
                gameWindow.updateCurrentRound(round);
            } catch (IOException e) {
                System.out.println("File not found. ");
                e.printStackTrace();
            }
        }
    }

    // MODIFIES: round
    // EFFECTS: starts the main game loop or allows the user to exit the program.
    public void playRound() {
        StringBuilder userInput;
        System.out.println("Type this phrase as quickly and as accurately as you can: ");
        System.out.println(round.getActualText());
        countdown(3);
        round.startRoundTime();
        System.out.print("\r> ");
        userInput = new StringBuilder(scanner.nextLine());
        round.setElapsedTime();
        System.out.println();
        afterRoundStats(userInput);
        System.out.println();
    }

    // MODIFIES: round
    // EFFECTS: displays the stats for the current round after it is over.
    public void afterRoundStats(StringBuilder userInput) {
        System.out.println("Time taken: " + round.getTimeTaken() + " seconds");
        round.setUserText(userInput);
        round.calculateAccuracy(round.getActualText(), round.getUserText());
        System.out.println("Accuracy: " + round.getAccuracy() + "%");
        round.setNumberOfUserTypedCharacters(userInput);
        round.setWordsPerMinute();
        System.out.println(round.getWordsPerMinute() + "wpm");
    }

    // EFFECTS: displays the options the user has in the console after the current round is over.
    public void printAfterRoundOptions() {
        System.out.println("This round is complete. What would you like to do next? ");
        System.out.println("- Enter 'P' to start a new round. ");
        System.out.println("- Enter 'S' to save phrase. ");
        System.out.println("- Enter 'R' to remove phrase. ");
        System.out.println("- Enter 'V' to view saved phrases. ");
        System.out.println("- Enter 'D' to download (save to file) saved phrases. ");
        System.out.println("- Enter 'L' to load saved phrases from file. ");
        System.out.println("- Enter 'E' to exit program. ");
        System.out.print("> ");
    }

    // EFFECTS: displays the options the user has in the console at the beginning of the game.
    public void printStartGameOptions() {
        System.out.println("---------------");
        System.out.println("| Typing Game |");
        System.out.println("---------------");
        System.out.println("- Enter 'S' to start a round. ");
        System.out.println("- Enter 'E' to exit program. ");
        System.out.print("> ");
    }

    // REQUIRES: duration > 0;
    // EFFECTS: creates a countdown of duration seconds. Used to give the player a few
    //          seconds to read the phrase they have to type.
    public void countdown(int duration) {
        long counterStartTime = System.nanoTime();
        long durationInNanoseconds = (long) duration * 1000000000;
        long endTime = counterStartTime + durationInNanoseconds;
        while (System.nanoTime() < endTime) {
            long remainingNanoseconds = endTime - System.nanoTime();
            long remainingSeconds = (int) (remainingNanoseconds / 1000000000);
            System.out.print("\r" + (remainingSeconds + 1));
        }
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // EFFECTS: saves user info (only saved phrases currently) to file
    public void saveUserInfo() {
        try {
            jsonWriter.open();
            jsonWriter.write(userInfo);
            jsonWriter.close();
            System.out.println("Saved phrases to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    
    // MODIFIES: this
    // EFFECTS: loads user info (only saved phrases currently) from file
    public void loadUserInfo() {
        try {
            userInfo = jsonReader.read();
            System.out.println("Loaded phrases from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
