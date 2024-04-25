package se.oru.wildfire;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    @Test
    void setGridSize() {
        Model model = new Model();
        int x = (int)(Math.random()*100);
        int y = (int)(Math.random()*100);
        model.setGridSize(x, y);
        Coordinate coordinate = model.getGridSize();
        assertEquals(x, coordinate.x());
        assertEquals(y, coordinate.y());
    }

    @Test
    void setInitialMap() {
        Model model = new Model();
        Cell[][] cells = new Cell[4][4];
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                cells[i][j] = new Cell((int)(Math.random()*100));
            }
        }
        InitialMap initialMap = new InitialMap(cells);
        model.setInitialMap(initialMap);
        Coordinate size = model.getGridSize();
        assertEquals(size.x(), 4);
        assertEquals(size.y(), 4);
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                assertEquals(cells[i][j], model.retrieveCell(i, j));
            }
        }
    }

    @Test
    void hasUpdate() {
        Model model = new Model();
        ObserverTestClass testObserver = new ObserverTestClass();
        model.registerListener(testObserver);
        model.hasUpdate();
        assertTrue(testObserver.isNotified);
    }

    @Test
    void updatedCells() {
        Model model = new Model();
        model.setGridSize(4, 4);
        NotifierTestClass notifierTestClass = new NotifierTestClass();
        Map<Coordinate, Cell> map = new HashMap<>();
        map.put(new Coordinate(3, 2), new Cell(10));
        notifierTestClass.setMap(map);
        model.newUpdate(notifierTestClass);
        Coordinate[] coordinates = model.updatedCells();
        assertEquals(1, coordinates.length);
        assertEquals(3, coordinates[0].x());
        assertEquals(2, coordinates[0].y());
    }

    @Test
    void retrieveCell() {
        Model model = new Model();
        Cell[][] cells = new Cell[4][4];
        int[][] values = new int[4][4];
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                int value = (int)(Math.random()*100);
                cells[i][j] = new Cell(value);
                values[i][j] = value;
            }
        }
        InitialMap initialMap = new InitialMap(cells);
        model.setInitialMap(initialMap);
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                Cell cell = model.retrieveCell(i, j);
                assertEquals(cell.burnedLevel(), values[i][j]);
            }
        }
    }
}
