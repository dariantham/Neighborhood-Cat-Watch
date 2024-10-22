package persistence;

import model.Cat;
import model.Neighborhood;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// NOTE: Based on the sample JsonSerializationDemo code provided from the course
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Neighborhood nh = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testReaderEmptyNeighborhood() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyNeighborhood.json");
        try {
            Neighborhood nh = reader.read();
            assertEquals(0, nh.getRescued());
            assertEquals(0, nh.getPresent());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralNeighborhood() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralNeighborhood.json");
        try {
            Neighborhood nh = reader.read();
            assertEquals(0, nh.getRescued());
            assertEquals(3, nh.getPresent());
            List<Cat> cats = nh.getCatList();
            assertEquals(3, cats.size());
            checkCat("Oreo", 0, 0, cats.get(0));
            checkCat("Sam", 0, 0, cats.get(1));
            checkCat("Tom", 0, 0, cats.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}