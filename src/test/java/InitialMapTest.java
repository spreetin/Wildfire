import org.junit.jupiter.api.*;
import se.oru.wildfire.Cell;
import se.oru.wildfire.Coordinate;
import se.oru.wildfire.InitialMap;

import static org.junit.jupiter.api.Assertions.*;

public class InitialMapTest {

    static InitialMap map;
    static Cell[][] cells;

    @BeforeAll
    static void setUp() {
        cells = new Cell[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                cells[i][j] = new Cell((int) (Math.random() * 100));
            }
        }
        map = new InitialMap(cells);
    }

    @Test
    void getSize() {
        Coordinate coordinate = map.getSize();
        assertEquals(coordinate.x(), 3);
        assertEquals(coordinate.y(), 3);
    }

    @Test
    void getCell() {
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                Cell cell = map.getCell(i, j);
                assertEquals(cell, cells[i][j]);
            }
        }
    }
}
