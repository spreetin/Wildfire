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

    public void setBaseState(Model model){
        Coordinate size = model.getGridSize();
        frontier.clear();
        for (int i=0;i<size.x();i++){
            for (int j=0;j<size.y();j++){
                if (model.retrieveCell(i, j).isBurning()){
                    if (!frontier.containsKey(new Coordinate(i, j))){
                        frontier.put(new Coordinate(i, j), model.retrieveCell(i, j));
                    }
                    if (j != 0){
                        if (!frontier.containsKey(new Coordinate(i, j-1))){
                            frontier.put(new Coordinate(i, j-1), model.retrieveCell(i, j-1));
                        }
                    }
                    if (i+1 < size.x()){
                        if (!frontier.containsKey(new Coordinate(i+1, j))){
                            frontier.put(new Coordinate(i+1, j), model.retrieveCell(i+1, j));
                        }
                    }
                    if (j+1 < size.y()){
                        if (!frontier.containsKey(new Coordinate(i, j+1))){
                            frontier.put(new Coordinate(i, j+1), model.retrieveCell(i, j+1));
                        }
                    }
                }
            }
        }
    }

    public void needUpdate(){
        updatedCells.clear();
        for (Coordinate coord : (frontier.keySet())){
            Cell cell = frontier.get(coord);
            // If the cell we are currently inspecting can't affect other cells continue to the next intreration
            if (!cell.isBurning()){
                continue;
            }
            // if the cell is burnt out remove it from the frontier as it cant be updated
            if (cell.burnedOut()){
                frontier.remove(coord);
                continue;
            }
            // Calculate any update, the boolean represents it being updated
            for (Coordinate near : (frontier.keySet())){
                // check all the values in the frontier and check if they are next to the current cell
                if(Math.abs(coord.x()-near.x())<=1 && (Math.abs(coord.y()-near.y())<=1) && (coord.x() != near.x() || coord.y() != near.y())){
                    // if we find a cell we want to update put that cell into updatedCells and update it
                    Cell nearCell = frontier.get(near);


                    if(!nearCell.burnedOut()){

                        Cell copy = new Cell(nearCell.burnedLevel());
                        updatedCells.put(near,copy);
                        // TODO Update this with a proper algorithm
                        copy.setBurnedLevel(nearCell.burnedLevel() + cell.burnedLevel());
                    }
                }

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
        if (!(o instanceof Model model))
            return;
        if (frontier.isEmpty()){
            setBaseState(model);
        } else {
            Coordinate[] modelUpdatedCells = model.updatedCells();
            for (Coordinate coordinate : modelUpdatedCells){
                if (!updatedCells.containsKey(coordinate)){
                    setBaseState(model);
                    return;
                }
            }
        }

    }
}
