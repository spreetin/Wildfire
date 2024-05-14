package se.oru.wildfire;

import java.util.*;

public class Calculator implements Observer, Notifier{

    final List<Observer> listeners;
    final Map<Coordinate, Cell> frontier;
    final Map<Coordinate, Cell> updatedCells;
    public Calculator(){
        listeners = new ArrayList<>();
        frontier = new HashMap<>();
        updatedCells = new HashMap<>();
    }

    Map<Coordinate, Cell> getFrontier(){
        return frontier;
    }

    public void setBaseState(Notifier model){
        frontier.clear();
        for (int i=0;;i++){
            if (model.retrieveCell(i, 0) == null)
                break;
            for (int j=0;;j++){
                if (model.retrieveCell(i, j) == null)
                    break;
                if (model.retrieveCell(i, j).isBurning()){
                    // This part is for updating the frontier
                    if (!frontier.containsKey(new Coordinate(i, j))){
                        frontier.put(new Coordinate(i, j), model.retrieveCell(i, j));
                    }
                    if (j != 0){
                        if (!frontier.containsKey(new Coordinate(i, j-1))){
                            frontier.put(new Coordinate(i, j-1), model.retrieveCell(i, j-1));
                        }
                    }
                    if (model.retrieveCell(i+1, j) != null){
                        if (!frontier.containsKey(new Coordinate(i+1, j))){
                            frontier.put(new Coordinate(i+1, j), model.retrieveCell(i+1, j));
                        }
                    }
                    if (model.retrieveCell(i, j+1) != null){
                        if (!frontier.containsKey(new Coordinate(i, j+1))){
                            frontier.put(new Coordinate(i, j+1), model.retrieveCell(i, j+1));
                        }
                    }
                    if (i != 0){
                        if (!frontier.containsKey(new Coordinate(i-1, j))){
                            frontier.put(new Coordinate(i-1, j), model.retrieveCell(i-1, j));
                        }
                    }
                    if (j != 0){
                        if (!frontier.containsKey(new Coordinate(i, j-1))){
                            frontier.put(new Coordinate(i, j-1), model.retrieveCell(i, j-1));
                        }
                    }
                }
            }
        }
    }

    public void needUpdate(){
        updatedCells.clear();
        for (Coordinate coord : (frontier.keySet())){
            // Create a copy of the cell in the frontier
            Cell cell = new Cell(frontier.get(coord));
            boolean isAffected = false;
            if (cell.isBurning()){
                cell.ignite();
                isAffected = true;
            } else {
                List<Cell> neighbours = getNeighbours(coord);
                for (Cell neighbour : neighbours){
                    if (neighbour != null && neighbour.isBurning()){
                        cell.ignite();
                        isAffected = true;
                    }
                }
            }
            if (isAffected){
                updatedCells.put(coord, cell);
            }
        }
        hasUpdate();
    }

    @Override
    public Coordinate[] updatedCells(){
        return updatedCells.keySet().toArray(new Coordinate[0]);
    }

    @Override
    public Cell retrieveCell(int x, int y){
        if (isDifferent(x, y)){
            return updatedCells.get(new Coordinate(x, y));
        }
        return null;
    }

    public boolean isDifferent(int x, int y){
        return updatedCells.containsKey(new Coordinate(x, y));
    }

    public void registerListener(Observer obj){
        listeners.add(obj);
    }

    private void hasUpdate(){
        for (Observer o: listeners) {
            o.newUpdate(this);
        }
    }

    @Override
    public void newUpdate(Notifier o) {
        // Cast o to Model class
        if (frontier.isEmpty()){
            setBaseState(o);
        } else {
            Coordinate[] modelUpdatedCells = o.updatedCells();
            for (Coordinate coordinate: modelUpdatedCells){
                frontier.put(coordinate, o.retrieveCell(coordinate));
            }
        }
    }
}
