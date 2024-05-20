package se.oru.wildfire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Notifier {

    Coordinate[] updatedCells();

    Cell retrieveCell(int x, int y);

    default Cell retrieveCell(Coordinate coordinate){
        return retrieveCell(coordinate.x(), coordinate.y());
    }

    default HashMap<Coordinate, Cell> getNeighbours(Coordinate coordinate){
        HashMap<Coordinate, Cell> neighbours = new HashMap<>();
        if (coordinate.x() > 0){
            Coordinate newCoord = new Coordinate(coordinate.x()-1, coordinate.y());
            neighbours.put(newCoord, retrieveCell(newCoord));
        }
        if (coordinate.y() > 0){
            Coordinate newCoord = new Coordinate(coordinate.x(), coordinate.y()-1);
            neighbours.put(newCoord, retrieveCell(newCoord));
        }
        if (retrieveCell(coordinate.x()+1, coordinate.y()) != null){
            Coordinate newCoord = new Coordinate(coordinate.x()+1, coordinate.y());
            neighbours.put(newCoord, retrieveCell(newCoord));
        }
        if (retrieveCell(coordinate.x(), coordinate.y()+1) != null){
            Coordinate newCoord = new Coordinate(coordinate.x(), coordinate.y()+1);
            neighbours.put(newCoord, retrieveCell(newCoord));
        }
        return neighbours;
    }

    boolean isDifferent(int x, int y);

    void registerListener(Observer obj);
}
