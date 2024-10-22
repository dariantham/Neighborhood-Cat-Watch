package persistence;

import model.Cat;

import static org.junit.jupiter.api.Assertions.assertEquals;

// NOTE: Based on the sample JsonSerializationDemo code provided from the course
public class JsonTest {
    protected void checkCat(String name, int daysFed, int daysPassed, Cat cat) {
        assertEquals(name, cat.getName());
        assertEquals(daysFed, cat.getDaysFed());
        assertEquals(daysPassed, cat.getDaysPassed());
    }
}
