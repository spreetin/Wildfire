package se.oru.wildfire;

import java.util.ArrayList;
import java.util.List;

public class Model implements Observer, Notifier {

    List<Observer> listeners = new ArrayList<>();
    Cell[][] map;
    List<Coordinate> updatedCells = new ArrayList<>();

    public void setGridSize(int x, int y){
        map = new Cell[x][y];
    }

    public Coordinate getGridSize(){
        if (map.length != 0){
            return new Coordinate(map.length, map[0].length);
        }
        return null;
    }

    public void setInitialMap(InitialMap initialMap){
        Coordinate size = initialMap.getSize();
        setGridSize(size.x(), size.y());
        for (int i=0;i<size.x();i++){
            for (int j=0;j<size.y();j++){
                map[i][j] = initialMap.getCell(i, j);
            }
        }
    }

    public void hasUpdate(){
        for (Observer o: listeners) {
           o.newUpdate(this);
        }
    }

    public void registerListener(Observer obj){
        listeners.add(obj);
    }

    @Override
    public boolean isDifferent(int x, int y){
        return updatedCells.contains(new Coordinate(x, y));
    }

    @Override
    public Coordinate[] updatedCells(){
        return updatedCells.toArray(new Coordinate[0]);
    }

    @Override
    public Cell retrieveCell(int x, int y){
        if (map == null)
            return null;
        if (map.length == 0)
            return null;
        if (x <= 0 || y <= 0 || x >= map.length || y >= map[0].length)
            return null;
        return map[x][y];
    }

    public Cell retrieveCell(Coordinate coordinate){
        return retrieveCell(coordinate.x(), coordinate.y());
    }

    @Override
    public void newUpdate(Notifier o) {
        updatedCells = List.of(o.updatedCells());
        for (Coordinate coordinate : updatedCells){
            map[coordinate.x()][coordinate.y()] = o.retrieveCell(coordinate.x(), coordinate.y());
        }
        hasUpdate();
    }
}
