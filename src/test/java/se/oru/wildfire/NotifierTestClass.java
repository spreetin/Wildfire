package se.oru.wildfire;

import java.util.Map;

public class NotifierTestClass implements Notifier{

    public Map<Coordinate,Cell> map;

    public void setMap(Map<Coordinate, Cell> map){
        this.map = map;
    }
    @Override
    public Coordinate[] updatedCells() {
        return map.keySet().toArray(new Coordinate[0]);
    }

    @Override
    public Cell retrieveCell(int x, int y) {
        if (map.containsKey(new Coordinate(x, y))){
            return map.get(new Coordinate(x, y));
        }
        return null;
    }

    @Override
    public boolean isDifferent(int x, int y) {
        return map.containsKey(new Coordinate(x, y));
    }

    @Override
    public void registerListener(Observer obj) {

    }
}
