package se.oru.wildfire;

import java.util.List;

public class Model implements Observer {

    List<Observer> listeners;
    Cell[][] map;

    public void setGridSize(int x, int y){
        // TODO: Implement
    }

    private void hasUpdate(){
        for (Observer o: listeners) {
           o.newUpdate(this);
        }
    }

    public void registerListener(Observer obj){
        listeners.add(obj);
    }

    public Coordinate[] updatedCells(){
        // TODO: Implement
        return new Coordinate[0];
    }

    public Cell retrieveCell(int x, int y){
        // TODO: Implement
        return null;
    }

    public Cell retrieveCell(Coordinate coordinate){
        return retrieveCell(coordinate.x(), coordinate.y());
    }

    @Override
    public void newUpdate(Object o) {

    }
}
