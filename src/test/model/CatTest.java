package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatTest {
    private Cat testCat;

    @BeforeEach
    void runBefore() {
        testCat = new Cat("Oreo");
    }

    @Test
    void testConstructor() {
        assertEquals("Oreo", testCat.getName());
        assertEquals(0, testCat.getDaysFed());
        assertEquals(0, testCat.getDaysPassed());
    }

    @Test
    void testIncreaseDaysFed() {
        testCat.increaseDaysFed();
        testCat.increaseDaysFed();
        testCat.increaseDaysFed();
        testCat.increaseDaysFed();
        assertEquals(4, testCat.getDaysFed());
        testCat.increaseDaysFed();
        testCat.increaseDaysFed();
        testCat.increaseDaysFed();
        testCat.increaseDaysFed();
        assertEquals(7, testCat.getDaysFed());
    }

    @Test
    void testIncreaseDaysPassed() {
        testCat.increaseDaysPassed();
        testCat.increaseDaysPassed();
        testCat.increaseDaysPassed();
        testCat.increaseDaysPassed();
        assertEquals(4, testCat.getDaysPassed());
        testCat.increaseDaysPassed();
        testCat.increaseDaysPassed();
        testCat.increaseDaysPassed();
        testCat.increaseDaysPassed();
        assertEquals(7, testCat.getDaysPassed());
    }


    @Test
    void testSendToVet() {
        assertFalse(testCat.sendToVet());
        testCat.increaseDaysPassed();
        testCat.increaseDaysPassed();
        testCat.increaseDaysPassed();
        testCat.increaseDaysPassed();
        assertFalse(testCat.sendToVet());
        testCat.increaseDaysPassed();
        testCat.increaseDaysPassed();
        testCat.increaseDaysPassed();
        testCat.increaseDaysFed();
        testCat.increaseDaysFed();
        testCat.increaseDaysFed();
        assertTrue(testCat.sendToVet());
        testCat.increaseDaysFed();
        assertTrue(testCat.sendToVet());
        testCat.increaseDaysFed();
        assertFalse(testCat.sendToVet());
    }
}