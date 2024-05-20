package se.oru.wildfire;

import java.util.HashMap;
import java.util.Map;

public class FrameStatistics implements Observer {

    int currentTick = 0;
    final Map<Integer, Map<Coordinate, Cell>> ticks = new HashMap<>();
    Cell[][] startingMap;

    public boolean hasTick(int tick){
        if(tick == 0 && startingMap != null){
            return true;
        }
        return ticks.containsKey(tick);
    }

    public void setStartingMap(Cell[][] map){
        startingMap = map;
    }

    public Cell[][] getTick(int tick){
        if(tick == 0){
            return startingMap;
        }
        int width = startingMap.length;
        if (width == 0)
            return null;
        int height = startingMap[0].length;
        if (height == 0)
            return null;
        Cell[][] result = startingMap;
        if (!ticks.containsKey(tick))
            return null;
        for (int i=1;i<=tick;i++){
            if (!ticks.containsKey(i))
                return null;
            Map<Coordinate, Cell> updatesInTick = ticks.get(tick);
            for (Coordinate coord : updatesInTick.keySet()){
                result[coord.x()][coord.y()] = updatesInTick.get(coord);
            }
        }
        return result;
    }

    public void setCurrentTick(int tick){
        currentTick = tick;
    }

    @Override
    public void newUpdate(Notifier o) {
        // Cast o to Model class
        Map<Coordinate, Cell> updatesInTick = new HashMap<>();
        Coordinate[] updatedCells = o.updatedCells();
        for (Coordinate coordinate : updatedCells){
            updatesInTick.put(coordinate, new Cell(o.retrieveCell(coordinate)));
        }
        currentTick++;
        // Clean out all superseded ticks.
        for (int i=currentTick;;i++){
            if (ticks.containsKey(i)) {
                ticks.remove(i);
            } else {
                break;
            }
        }
        ticks.put(currentTick, updatesInTick);
    }

    public int getCurrentTick(){
        return currentTick;
    }
}
