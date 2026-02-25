package persistance;

import model.UserInfo;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of the user's info to file.
public class JsonWriter {

    // EFFECTS: constructs a writer to write to the destination file. 
    public JsonWriter(String destination) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(UserInfo userInfo) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        // stub
    }



}
