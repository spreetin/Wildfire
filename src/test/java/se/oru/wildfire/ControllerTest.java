package se.oru.wildfire;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerTest {

    static Controller controller;

    @BeforeAll
    static void setUp(){
        controller = new Controller(new View(), null);
    }

    @Test
    @Order(1)
    void startTimer() {
        controller.startTimer();
        assertTrue(controller.timer.isRunning());
    }

    @Test
    @Order(2)
    void pauseTimer() {
        controller.pauseTimer();
        assertFalse(controller.timer.isRunning());
    }
}
