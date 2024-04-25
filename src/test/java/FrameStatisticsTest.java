import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.oru.wildfire.Cell;
import se.oru.wildfire.FrameStatistics;

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
        // Need additional setup
    }

    @AfterAll
    static void tearDown(){

    }

    @Test
    void hasTick() {
    }

    @Test
    void getTick() {
    }

    @Test
    void newUpdate() {
    }

    @Test
    void setCurrentTick() {
    }
}
