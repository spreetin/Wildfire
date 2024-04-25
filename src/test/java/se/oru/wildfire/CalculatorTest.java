package se.oru.wildfire;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import se.oru.wildfire.Calculator;
import se.oru.wildfire.Model;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    static Calculator calculator;
    static Model model;

    @BeforeAll
    static void setUp() {
        calculator = new Calculator();
        model = new Model();
        model.setGridSize(2, 2);
        Cell[][] cells = new Cell[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                Cell cell;
                if (i == 0 && j == 0){
                    cell = new Cell(80);
                } else {
                    cell = new Cell(0);
                }
                cells[i][j] = cell;
            }
        }
        InitialMap initialMap = new InitialMap(cells);
        model.setInitialMap(initialMap);
    }

    @Test
    @Order(1)
    void setBaseState() {
        calculator.setBaseState(model);
        Map<Coordinate, Cell> frontier = calculator.getFrontier();
        assertTrue(frontier.containsKey(new Coordinate(0, 0)));
        assertTrue(frontier.containsKey(new Coordinate(0, 1)));
        assertTrue(frontier.containsKey(new Coordinate(1, 0)));
        assertFalse(frontier.containsKey(new Coordinate(1, 1)));
        assertFalse(frontier.containsKey(new Coordinate(1, 2)));
        assertFalse(frontier.containsKey(new Coordinate(2, 1)));
        assertFalse(frontier.containsKey(new Coordinate(2, 2)));
    }

    @Test
    @Order(2)
    void needUpdate() {
        Map<Coordinate, Cell> frontier = calculator.getFrontier();
        needUpdate();
        assertNotEquals(frontier, calculator.getFrontier());
    }

    @Test
    @Order(3)
    void isDifferent() {
        assertTrue(calculator.isDifferent(0, 0));
        assertTrue(calculator.isDifferent(1, 0));
        assertTrue(calculator.isDifferent(0, 1));
        assertFalse(calculator.isDifferent(1, 1));
        assertFalse(calculator.isDifferent(1, 2));
        assertFalse(calculator.isDifferent(0, 2));
    }

    @Test
    @Order(4)
    void retrieveUpdatedCell() {
        Cell cell = calculator.retrieveUpdatedCell(0, 0);
        assertNotEquals(cell.burnedLevel(), 80);
        assertEquals(cell.burnedLevel(), 90);
    }
}
