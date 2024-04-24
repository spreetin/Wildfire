import java.util.HashMap;
import java.util.Map;

public class View implements Observer{
    @Override
    public void newUpdate(Object o) {
        // Cast o to Model class
        if (!(o instanceof Model model))
            return;
        Coordinate[] updatedCells = model.updatedCells();
        // TODO: Implement
    }
}
