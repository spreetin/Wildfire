package se.oru.wildfire;

import javafx.scene.layout.GridPane;
import se.oru.wildfire.Coordinate;
import se.oru.wildfire.Observer;

public class View extends GridPane implements Observer {
    @Override
    public void newUpdate(Notifier o) {
        // Cast o to Model class
        Coordinate[] updatedCells = o.updatedCells();
        // TODO: Implement
    }
}
