package se.oru.wildfire;

import java.util.*;
public class Calculator implements Observer, Notifier{

    public enum WindDirection {
        North,
        East,
        South,
        West,
        None
    }

    final List<Observer> listeners;
    final Map<Coordinate, Cell> frontier;
    final Map<Coordinate, Cell> updatedCells;
    WindDirection windDirection = WindDirection.None;
    boolean hasWind = false;
    double windIntensity = 0.0;
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
        Random random = new Random();
        for (Coordinate coord : (frontier.keySet())){
            // Create a copy of the cell in the frontier
            Cell cell = new Cell(frontier.get(coord));
            if (cell.getGroundType() != Cell.GroundType.Trees){
                continue;
            }
            if (cell.burnedOut()){
                continue;
            }
            int burnChange = 0;
            HashMap<Coordinate,Cell> neighbours = new HashMap<>();
            Coordinate[] neighbourCoordinates = {new Coordinate(coord.x()-1, coord.y()),
                                                new Coordinate(coord.x()+1, coord.y()),
                                                new Coordinate(coord.x(), coord.y()-1),
                                                new Coordinate(coord.x(), coord.y()+1),
                                                new Coordinate(coord.x() +1, coord.y()+1),
                                                new Coordinate(coord.x() -1, coord.y()-1),
                                                new Coordinate(coord.x() +1, coord.y()-1),
                                                new Coordinate(coord.x() -1, coord.y()+1)
            };

            for (Coordinate neighbourCoordinate : neighbourCoordinates){
                if (frontier.containsKey(neighbourCoordinate)){
                    neighbours.put(neighbourCoordinate, frontier.get(neighbourCoordinate));
                }
            }
            for (Coordinate coordinate : neighbours.keySet()){
                Cell neighbour = neighbours.get(coordinate);

                if (neighbour != null && neighbour.canSpread()) {
                    double spreadingProbability = 0.3;
                    if (hasWind && windDirection != WindDirection.None) {
                        switch (windDirection) {
                            case North:
                                if (coordinate.y() > coord.y()) {
                                    spreadingProbability += 0.3;
                                } else if (coordinate.y() < coord.y()) {
                                    spreadingProbability -= windIntensity;
                                }
                                break;
                            case South:
                                if (coordinate.y() < coord.y()) {
                                    spreadingProbability += 0.3;
                                } else if (coordinate.y() > coord.y()) {
                                    spreadingProbability -= windIntensity;
                                }
                                break;
                            case East:
                                if (coordinate.x() < coord.x()) {
                                    spreadingProbability += 0.3;
                                } else if (coordinate.x() > coord.x()) {
                                    spreadingProbability -= windIntensity;
                                }
                                break;
                            case West:
                                if (coordinate.x() > coord.x()) {
                                    spreadingProbability += 0.3;
                                } else if (coordinate.x() < coord.x()) {
                                    spreadingProbability -= windIntensity;
                                }
                                break;
                        }
                    }
                    if(random.nextDouble() < spreadingProbability){
                        burnChange += 10;
                    }
                }

                if (neighbour != null && neighbour.getGroundType() == Cell.GroundType.Water){
                    burnChange -= 15;
                }
            }
            if (cell.isBurning()){
                burnChange += 10;
            }
            if(burnChange != 0) {
                cell.setBurnedLevel(cell.burnedLevel() + burnChange);
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

    @Override
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
        if (frontier.isEmpty()){
            setBaseState(o);
        } else {
            frontier.clear();
            Coordinate[] modelUpdatedCells = o.updatedCells();
            for (Coordinate coordinate: modelUpdatedCells){
                if (!frontier.containsKey(coordinate))
                    frontier.put(coordinate, o.retrieveCell(coordinate));
                HashMap<Coordinate, Cell> neighbours = o.getNeighbours(coordinate);
                for (Coordinate neighbour : neighbours.keySet()){
                    if (!frontier.containsKey(neighbour)){
                        frontier.put(neighbour, neighbours.get(neighbour));
                    }
                }
            }
        }
    }

    void setWindDirection(WindDirection direction){
        windDirection = direction;
    }

    void setHasWind(boolean wind){
        hasWind = wind;
    }

    void setWindIntensity(double intensity){
        windIntensity = intensity;
    }

    void setInitialMap(InitialMap map){
        frontier.clear();
        Coordinate size = map.getSize();
        for (int i=0;i<size.x();i++){
            for (int j=0;j<size.y();j++){
                if (map.getCell(i, j).isBurning()){
                    for (int k=i-1;k<=i+1;k++){
                        for (int l=j-1;l<=j+1;l++){
                            if (map.getCell(k, l) != null && !frontier.containsKey(new Coordinate(k, l))){
                                frontier.put(new Coordinate(k, l), map.getCell(k, l));
                            }
                        }
                    }
                }
            }
        }
    }
}
