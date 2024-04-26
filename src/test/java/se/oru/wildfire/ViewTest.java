package se.oru.wildfire;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ViewTest {
    private View view;
    private InitialMap initialMap;

    @BeforeEach
    public void setUp() {
        view = new View();
        Cell[][] cells = new Cell[3][3];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setGroundType(Cell.GroundType.Trees); //set all cells to groundtype trees.
            }
        }
        cells[2][2].setGroundType(Cell.GroundType.Stone); // set 1 cell to groundtype Stone.
        initialMap = new InitialMap(cells);
    }

    @Test
    public void setInitialMapTest() {
        view.setInitialMap(initialMap);
        Rectangle rect = view.getCells()[0][0];
        assertNotNull(rect); // make sure not null
        assertEquals(Color.DARKGREEN, rect.getFill()); // make sure cell[0][0] is Green
        rect = view.getCells()[2][2]; // update to a new cell
        assertEquals(Color.DARKGREY, rect.getFill()); // make sure cell[0][0] is Green
    }

    @Test
    void newUpdateTest() {
        fail();
    }

}
