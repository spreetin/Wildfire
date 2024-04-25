package se.oru.wildfire;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.oru.wildfire.Calculator;
import se.oru.wildfire.Model;

import static org.junit.jupiter.api.Assertions.fail;

public class CalculatorTest {

    static Calculator calculator;
    static Model model;

    @BeforeAll
    static void setUp() {
        calculator = new Calculator();
        model = new Model();
        model.setGridSize(2, 2);
        model.
    }

    @Test
    void needUpdate() {
        fail();
    }

    @Test
    void isDifferent() {
        fail();
    }

    @Test
    void retrieveUpdatedCell() {
        fail();
    }
}
