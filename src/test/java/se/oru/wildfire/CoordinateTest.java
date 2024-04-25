package se.oru.wildfire;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.oru.wildfire.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {

    private static Coordinate coord;
    private static int x;
    private static int y;

    @BeforeAll
    static void setUp() {
        x = (int) (Math.random() * 500);
        y = (int) (Math.random() * 500);
        coord = new Coordinate(x, y);
    }

    @Test
    @DisplayName("Test x-coordinate")
    void x() {
        assertEquals(x, coord.x());
    }

    @Test
    @DisplayName("Test y-coordinate")
    void y() {
        assertEquals(y, coord.y());
    }

    @Test
    @DisplayName("Test equality operator")
    void testEquals() {
        int x1 = 1, x2 = 5;
        int y1 = 5, y2 = 26;
        Coordinate c1 = new Coordinate(x1, y1);
        assertEquals(c1, new Coordinate(x1, y1));
        assertNotEquals(c1, new Coordinate(x2, y2));
        //noinspection SuspiciousNameCombination
        assertNotEquals(c1, new Coordinate(y1, x1));
    }

    @Test
    @DisplayName("Test hash key generator")
    void testHashCode() {
        int x1 = 1, x2 = 5;
        int y1 = 5, y2 = 26;
        Coordinate c1 = new Coordinate(x1, y1);
        assertEquals(c1.hashCode(), new Coordinate(x1, y1).hashCode());
        //noinspection SuspiciousNameCombination
        assertNotEquals(c1.hashCode(), new Coordinate(y1, x1).hashCode());
        assertNotEquals(c1.hashCode(), new Coordinate(x2, y2).hashCode());
    }
}
