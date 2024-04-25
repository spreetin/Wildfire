package se.oru.wildfire;

import java.util.List;

public class Model implements Observer, Notifier {

    List<Observer> listeners;
    Cell[][] map;

    public void setGridSize(int x, int y){
        // TODO: Implement
    }

    public Coordinate getGridSize(){
        // TODO: Implement
        return null;
    }

    public void setInitialMap(InitialMap map){
        // TODO: Implement
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
        // TODO: Implement
        return false;
    }

    @Override
    public Coordinate[] updatedCells(){
        // TODO: Implement
        return new Coordinate[0];
    }

    @Override
    public Cell retrieveCell(int x, int y){
        // TODO: Implement
        return null;
    }

    public Cell retrieveCell(Coordinate coordinate){
        return retrieveCell(coordinate.x(), coordinate.y());
    }

    @Override
    public void newUpdate(Notifier o) {

    }
}
