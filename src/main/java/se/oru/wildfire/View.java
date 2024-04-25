package se.oru.wildfire;

import se.oru.wildfire.Coordinate;
import se.oru.wildfire.Observer;

public class View implements Observer {
    @Override
    public void newUpdate(Notifier o) {
        // Cast o to Model class
        Coordinate[] updatedCells = o.updatedCells();
        // TODO: Implement
    }
}
