import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    private static Cell cell;
    private static int value;

    @BeforeAll
    static void setUp() {
        value = (int) (Math.random() * 100);
        cell = new Cell();
        cell.setBurnedLevel(value);
    }

    @Test
    @DisplayName("boolean burnedOut()")
    void burnedOut() {
        assertEquals(value >= 100, cell.burnedOut());
    }

    @Test
    @DisplayName("bool isBurning()")
    void isBurning() {
        assertEquals(value != 0, cell.isBurning());
    }

    @Test
    @DisplayName("int burnedLevel()")
    void burnedLevel() {
        assertEquals(value, cell.burnedLevel());
    }

    @Test
    @DisplayName("Neighbour burning")
    void setNeighbourBurning() {
        cell.setNeighbourBurning();
        assertEquals(value + 10, cell.burnedLevel());
        if (cell.burnedLevel() >= 100){
            assertTrue(cell.burnedOut());
        } else {
            assertFalse(cell.burnedOut());
        }
        cell.setBurnedLevel(cell.burnedLevel() - 10);
    }
}
