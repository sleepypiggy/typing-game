package model;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import java.util.ArrayList;

// The UserInfo class represents all the information of the user,
// including the past rounds that the user has played (where each round
// stores individual information, although this part isn't implemented yet as 
// I want to figure out what to do with it), as well as saved phrases that the user
// can look back on.

public class UserInfo implements Writable {

    ArrayList<Round> pastRounds;
    ArrayList<String> savedPhrases;
    
    public UserInfo() {
        this.pastRounds = new ArrayList<>();
        this.savedPhrases = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds phrase to the list of saved phrases, savedPhrases. If phrase is 
    //          already in savedPhrases, do nothing.
    public void addSavedPhrase(StringBuilder phrase) {
        if (!this.savedPhrases.contains(phrase.toString())) {
            this.savedPhrases.add(phrase.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds round to the list of past rounds played, pastRounds.
    public void addPastRound(Round round) {
        this.pastRounds.add(round);
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("phrases", phrasesToJson());
        return json;        
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // EFFECTS: returns phrase as a JSON object.
    public JSONObject phraseToJson(String phrase) {
        JSONObject json = new JSONObject();
        json.put("phrase", phrase);
        return json;
    }

    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // EFFECTS: returns things in this user info as a JSON array.
    private JSONArray phrasesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String s : savedPhrases) {
            jsonArray.put(phraseToJson(s));
        }

        return jsonArray;
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
