package se.oru.wildfire;

import java.util.ArrayList;
import java.util.List;

public interface Notifier {

    Coordinate[] updatedCells();

    Cell retrieveCell(int x, int y);

    default Cell retrieveCell(Coordinate coordinate){
        return retrieveCell(coordinate.x(), coordinate.y());
    }

    default List<Cell> getNeighbours(Coordinate coordinate){
        ArrayList<Cell> neighbours = new ArrayList<>();
        if (coordinate.x() > 0){
            neighbours.add(retrieveCell(coordinate.x()-1, coordinate.y()));
        }
        if (coordinate.y() > 0){
            neighbours.add(retrieveCell(coordinate.x(), coordinate.y()-1));
        }
        if (retrieveCell(coordinate.x()+1, coordinate.y()) != null){
            neighbours.add(retrieveCell(coordinate.x()+1, coordinate.y()));
        }
        if (retrieveCell(coordinate.x(), coordinate.y()+1) != null){
            neighbours.add(retrieveCell(coordinate.x(), coordinate.y()+1));
        }
        return neighbours;
    }

    boolean isDifferent(int x, int y);
}
