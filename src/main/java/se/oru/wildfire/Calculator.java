package se.oru.wildfire;

import java.util.*;

public class Calculator {

    List<Observer> listeners;
    Map<Coordinate, Cell> frontier;
    Map<Coordinate, Cell> updatedCells;

    public Calculator(){
        listeners = new ArrayList<>();
        frontier = new HashMap<>();
        updatedCells = new HashMap<>();
    }

    public void needUpdate(){
        updatedCells.clear();
        for (Coordinate coord : frontier.keySet()){
            Cell cell = frontier.get(coord);
            boolean updated = false;
            // Calculate any update, the boolean represents it being updated
            if (updated){
                updatedCells.put(coord, cell);
                // Add any cell that is !burnedOut and is a neighbour to the frontier
            } else {
                if (cell.burnedOut()){
                    frontier.remove(coord);
                }
            }
        }
        hasUpdate();
    }

    public boolean isDifferent(int x, int y){
        return updatedCells.containsKey(new Coordinate(x, y));
    }

    public Cell retrieveUpdatedCell(int x, int y){
        if (isDifferent(x, y)){
            return updatedCells.get(new Coordinate(x, y));
        }
        return null;
    }

    public void registerListener(Observer obj){
        listeners.add(obj);
    }

    private void hasUpdate(){
        for (Observer o: listeners) {
            o.newUpdate(this);
        }
    }
}
