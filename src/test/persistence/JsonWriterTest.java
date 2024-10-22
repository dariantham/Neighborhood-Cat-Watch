package persistence;

import model.Cat;
import model.Neighborhood;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// NOTE: Based on the sample JsonSerializationDemo code provided from the course
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Neighborhood nh = new Neighborhood();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testWriterEmptyNeighborhood() {
        try {
            Neighborhood nh  = new Neighborhood();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyNeighborhood.json");
            writer.open();
            writer.write(nh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyNeighborhood.json");
            nh = reader.read();
            assertEquals(0, nh.getRescued());
            assertEquals(0, nh.getPresent());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralNeighborhood() {
        try {
            Neighborhood nh = new Neighborhood();
            nh.addCat(new Cat("Oreo"));
            nh.addCat(new Cat("Sam"));
            nh.addCat(new Cat("Tom"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralNeighborhood.json");
            writer.open();
            writer.write(nh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralNeighborhood.json");
            nh = reader.read();
            assertEquals(0, nh.getRescued());
            assertEquals(3, nh.getPresent());
            List<Cat> cats = nh.getCatList();
            assertEquals(3, cats.size());
            checkCat("Oreo", 0, 0, cats.get(0));
            checkCat("Sam", 0, 0, cats.get(1));
            checkCat("Tom", 0, 0, cats.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
