package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class NeighborhoodTest {
    private Neighborhood testNeighborhood;
    private Cat cat1;
    private Cat cat2;
    private Cat cat3;

    @BeforeEach
    void runBefore() {
        testNeighborhood = new Neighborhood();
        cat1 = new Cat("Oreo");
        cat2 = new Cat("Sam");
        cat3 = new Cat("KC");
    }

    @Test
    void testConstructor() {
        assertTrue(testNeighborhood.getCatList().isEmpty());
        assertEquals(0, testNeighborhood.getPresent());
        assertEquals(0, testNeighborhood.getRescued());
    }

    @Test
    void testAddCat() {
        testNeighborhood.addCat(cat1);
        assertEquals(cat1, testNeighborhood.getCatList().get(0));
        assertEquals(1, testNeighborhood.getPresent());
        testNeighborhood.addCat(cat2);
        assertEquals(cat2, testNeighborhood.getCatList().get(1));
        assertEquals(2, testNeighborhood.getPresent());
    }

    @Test
    void testDeleteCat() {
        testNeighborhood.addCat(cat1);
        testNeighborhood.addCat(cat2);
        testNeighborhood.addCat(cat3);
        testNeighborhood.deleteCat(cat2);
        assertEquals(cat1, testNeighborhood.getCatList().get(0));
        assertEquals(cat3, testNeighborhood.getCatList().get(1));
        assertEquals(2, testNeighborhood.getCatList().size());
    }

    @Test
    void testFedToday() {
        testNeighborhood.addCat(cat1);
        testNeighborhood.fedToday(cat1);
        assertEquals(1, cat1.getDaysFed());
        assertEquals(1, cat1.getDaysPassed());
        testNeighborhood.fedToday(cat1);
        testNeighborhood.fedToday(cat1);
        assertEquals(3, cat1.getDaysFed());
        assertEquals(3, cat1.getDaysPassed());
        testNeighborhood.addCat(cat2);
        testNeighborhood.fedToday(cat2);
        testNeighborhood.fedToday(cat2);
        assertEquals(2, cat2.getDaysFed());
        assertEquals(2, cat2.getDaysPassed());
        assertEquals(3, cat1.getDaysFed());
        assertEquals(3, cat1.getDaysPassed());
    }

    @Test
    void testNotFedToday() {
        testNeighborhood.addCat(cat1);
        testNeighborhood.notFedToday(cat1);
        assertEquals(1, cat1.getDaysPassed());
        testNeighborhood.addCat(cat2);
        testNeighborhood.notFedToday(cat1);
        assertEquals(2, cat1.getDaysPassed());
    }
}
