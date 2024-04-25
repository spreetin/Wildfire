package se.oru.wildfire;

import se.oru.wildfire.Cell;
import se.oru.wildfire.Coordinate;

public class InitialMap {

    private Cell[][] map;

    public InitialMap(Cell[][] mapData){
        map = mapData;
    }

    public Coordinate getSize(){
        // TODO: Implement
        return null;
    }

    public Cell getCell(int x, int y){
        // TODO: Implement
        return null;
    }

    public Cell getCell(Coordinate coordinate){
        return getCell(coordinate.x(), coordinate.y());
    }
}
