package se.oru.wildfire;

import se.oru.wildfire.Cell;
import se.oru.wildfire.Coordinate;

public class InitialMap {

    private Cell[][] map;

    public InitialMap(Cell[][] mapData){
        map = mapData;
    }

    /**
     * Inputs Type Model
     * @return Type Coordinate
     */
    public Coordinate getSize(){
        int x = this.map.length;
        int y = this.map[0].length;
        Coordinate size = new Coordinate(x, y);
        return size;
    }

    /**
     * @param x
     * @param y
     * @return Type Cell
     */
    public Cell getCell(int x, int y){
        return map[x][y];
    }

    public Cell getCell(Coordinate coordinate){
        return getCell(coordinate.x(), coordinate.y());
    }
}
