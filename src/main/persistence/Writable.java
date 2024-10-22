package persistence;

import org.json.JSONObject;

// NOTE: Based on the sample JsonSerializationDemo code provided from the course
// Interface for returning JSON object
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
