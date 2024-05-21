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
        for (int i=coordinate.x()-1; i<=coordinate.x()+1; i++){
            for (int j=coordinate.y()-1; j<=coordinate.y()+1; j++){
                if (i >= 0 && j >= 0 && retrieveCell(i, j) != null){
                    neighbours.put(new Coordinate(i, j), retrieveCell(i, j));
                }

            }
        }
        return neighbours;
    }

    boolean isDifferent(int x, int y);

    void registerListener(Observer obj);
}
