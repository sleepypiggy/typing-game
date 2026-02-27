package persistence;

import model.UserInfo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads user info from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads user info from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserInfo read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUserInfo(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses user info from JSON object and returns it
    private UserInfo parseUserInfo(JSONObject jsonObject) {
        UserInfo userInfo = new UserInfo();
        addPhrases(userInfo, jsonObject);
        return userInfo;
    }

    // MODIFIES: userInfo
    // EFFECTS: parses phrases from JSON object and adds them to user info
    private void addPhrases(UserInfo userInfo, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("phrases");
        for (Object json : jsonArray) {
            JSONObject nextPhrase = (JSONObject) json;
            addPhrase(userInfo, nextPhrase);
        }
    }

    // MODIFIES: userInfo
    // EFFECTS: parses phrase from JSON object and adds it to user info
    private void addPhrase(UserInfo userInfo, JSONObject jsonObject) {
        String phrase = jsonObject.getString("phrase");
        userInfo.addSavedPhrase(new StringBuilder(phrase));
    }


}
