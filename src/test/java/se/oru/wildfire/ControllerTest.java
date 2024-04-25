package se.oru.wildfire;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    static Controller controller;

    @BeforeAll
    static void setUp(){
        controller = new Controller();
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
