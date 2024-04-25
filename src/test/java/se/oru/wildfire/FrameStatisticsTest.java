package se.oru.wildfire;

import org.junit.jupiter.api.*;
import se.oru.wildfire.Cell;
import se.oru.wildfire.FrameStatistics;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FrameStatisticsTest {

    static FrameStatistics fs;

    @BeforeAll
    static void setUp(){
        fs = new FrameStatistics();
        Cell[][] map = new Cell[2][2];
        map[0][0] = new Cell(80);
        map[0][1] = new Cell(70);
        map[1][0] = new Cell(60);
        map[1][1] = new Cell(90);
        fs.setStartingMap(map);
    }

    @Test
    @Order(1)
    void newUpdate() {
        assertEquals(0, fs.currentTick);
        NotifierTestClass notifierTestClass = new NotifierTestClass();
        Map<Coordinate, Cell> map = new HashMap<>();
        map.put(new Coordinate(1, 1), new Cell(10));
        notifierTestClass.setMap(map);
        fs.newUpdate(notifierTestClass);
        assertEquals(1, fs.currentTick);
        // TODO: More tests, for the special case
    }

    @Test
    @Order(2)
    void hasTick() {
        assertTrue(fs.hasTick(1));
    }

    @Test
    @Order(3)
    void getTick() {
        Cell[][] tick = fs.getTick(1);
        assertEquals(80, tick[0][0].burnedLevel());
        assertEquals(70, tick[0][1].burnedLevel());
        assertEquals(60, tick[1][0].burnedLevel());
        assertEquals(10, tick[1][1].burnedLevel());
    }

    @Test
    @Order(4)
    void setCurrentTick() {
        assertEquals(1, fs.currentTick);
        fs.setCurrentTick(0);
        assertEquals(0, fs.currentTick);
    }
}
