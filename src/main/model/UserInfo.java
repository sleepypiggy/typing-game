package model;

import java.util.ArrayList;
import exceptions.DuplicatePhraseException;

// The UserInfo class represents all the information of the user,
// including the past rounds that the user has played (where each round
// stores individual information), as well as saved phrases that the user
// replay.

public class UserInfo {

    ArrayList<Round> pastRounds;
    ArrayList<String> savedPhrases;
    
    public UserInfo() {
        this.pastRounds = new ArrayList<>();
        this.savedPhrases = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds phrase to the list of saved phrases, savedPhrases. If phrase is 
    //          already in savedPhrases, don't add it and notify user.
    public void addSavedPhrase(StringBuilder phrase) {
        try {
            if (!this.savedPhrases.contains(phrase.toString())) {
                this.savedPhrases.add(phrase.toString());
            } else {
                throw new DuplicatePhraseException();
            }
        } catch (DuplicatePhraseException e) {
            System.out.println("You already saved this phrase. ");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds round to the list of past rounds played, pastRounds.
    public void addPastRound(Round round) {
        this.pastRounds.add(round);
    }

    public ArrayList<String> getSavedPhrases() {
        return savedPhrases;
    }

    public int getNumberOfSavedPhrases() {
        return savedPhrases.size();
    }

    public ArrayList<Round> getPastRounds() {
        return pastRounds;
    }

    public int getNumberOfPastRounds() {
        return pastRounds.size();
    }

}
