package persistence;

import model.Cat;
import model.Neighborhood;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads neighborhood from JSON data stored in file
// NOTE: Based on the sample JsonSerializationDemo code provided from the course
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Neighborhood read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseNeighborhood(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses neighborhood from JSON object and returns it
    private Neighborhood parseNeighborhood(JSONObject jsonObject) {
        Neighborhood nh = new Neighborhood();
        nh.setRescued(jsonObject.getInt("rescued"));
        nh.setPresent(jsonObject.getInt("present"));
        addCats(nh, jsonObject);
        return nh;
    }

    // MODIFIES: nh
    // EFFECTS: parses cats from JSON object and adds them to neighborhood
    private void addCats(Neighborhood nh, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cats");
        for (Object json : jsonArray) {
            JSONObject nextCat = (JSONObject) json;
            String name = nextCat.getString("name");
            int daysFed = nextCat.getInt("daysFed");
            int daysPassed = nextCat.getInt("daysPassed");

            Cat cat = new Cat(name);
            cat.setDaysFed(daysFed);
            cat.setDaysPassed(daysPassed);

            nh.getCatList().add(cat);
        }
    }
}
