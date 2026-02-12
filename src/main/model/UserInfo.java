package model;

import java.util.ArrayList;

// The UserInfo class represents all the information of the user,
// including the past rounds that the user has played (where each round
// stores individual information), as well as saved phrases that the user
// replay.

public class UserInfo {

    ArrayList<Round> pastRounds;
    ArrayList<String> savedPhrases;
    
    public UserInfo() {

    }

    // MODIFIES: this
    // EFFECTS: adds phrase to the list of saved phrases, savedPhrases.
    public void addSavedPhrase(String phrase) {

    }

    // MODIFIES: this
    // EFFECTS: adds round to the list of past rounds played, pastRounds.
    public void addPastRound(Round round) {

    }

}
