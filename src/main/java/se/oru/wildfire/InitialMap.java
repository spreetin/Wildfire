package se.oru.wildfire;

import se.oru.wildfire.Cell;
import se.oru.wildfire.Coordinate;

public class InitialMap {

    private final Cell[][] map;

    public InitialMap(Cell[][] mapData){
        map = deepCopyMap(mapData);
    }

    /**
     * Inputs Type Model
     * @return Type Coordinate
     */
    public Coordinate getSize(){
        int x = this.map.length;
        int y = this.map[0].length;
        return new Coordinate(x, y);
    }

    /**
     * @param x
     * @param y
     * @return Type Cell
     */
    public Cell getCell(int x, int y){
        if (x < 0 || y < 0){
            return null;
        }
        return map[x][y];
    }

    public Cell getCell(Coordinate coordinate){
        return getCell(coordinate.x(), coordinate.y());
    }

    public Cell[][] getMap(){
        return map;
    }

    public Cell[][] deepCopyMap(Cell[][] map){
        Cell[][] result = new Cell[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                result[i][j] = new Cell(map[i][j]);
            }
        }
        return result;
    }
}
