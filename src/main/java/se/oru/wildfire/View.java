package se.oru.wildfire;

import se.oru.wildfire.Coordinate;
import se.oru.wildfire.Observer;

public class View implements Observer {
    @Override
    public void newUpdate(Object o) {
        // Cast o to Model class
        if (!(o instanceof Model model))
            return;
        Coordinate[] updatedCells = model.updatedCells();
        // TODO: Implement
    }
}
