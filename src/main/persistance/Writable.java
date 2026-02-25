package persistance;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: return this as a JSON object. 
    JSONObject toJson();
}
