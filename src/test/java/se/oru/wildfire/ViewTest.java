package se.oru.wildfire;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ViewTest {

    @Test
    @Order(1)
    public void setInitialMap() {
        View view = new View();
        Cell[][] cells = new Cell[3][3];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setGroundType(Cell.GroundType.Trees); //set all cells to groundtype trees.
            }
        }
        cells[2][2].setGroundType(Cell.GroundType.Stone); // set 1 cell to groundtype Stone.
        InitialMap initialMap = new InitialMap(cells);
        view.setInitialMap(initialMap);
        Rectangle rect = view.getCells()[0][0];
        assertNotNull(rect); // make sure not null
        assertEquals(Color.DARKGREEN, rect.getFill()); // make sure cell[0][0] is Green
        rect = view.getCells()[2][2]; // update to a new cell
        assertEquals(Color.DARKGREY, rect.getFill()); // make sure cell[0][0] is Green
    }

    @Test
    @Order(3)
    void newUpdate() {
        fail();
    }

    @Test
    @Order(2)
    void drawCell(){
        View view = new View();
        Cell[][] cells = new Cell[3][3];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setGroundType(Cell.GroundType.Trees); //set all cells to groundtype trees.
            }
        }
        cells[2][2].setGroundType(Cell.GroundType.Stone); // set 1 cell to groundtype Stone.
        InitialMap initialMap = new InitialMap(cells);
        view.setInitialMap(initialMap);
        Cell cell = new Cell();
        cell.setGroundType(Cell.GroundType.Water);
        view.drawCell(cell, new Coordinate(2, 2));
        assertEquals(Color.DARKBLUE, view.getCells()[2][2].getFill());
    }

    @Test
    @Order(4)
    void handleCellClick(){
        fail();
    }
}
