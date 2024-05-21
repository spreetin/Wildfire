package se.oru.wildfire;

import java.util.ArrayList;
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
        startingMap = deepCopyMap(map);
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
        Cell[][] result = deepCopyMap(startingMap);
        if (!ticks.containsKey(tick))
            return null;
        for (int i=1;i<=tick;i++){
            if (!ticks.containsKey(i))
                return null;
            Map<Coordinate, Cell> updatesInTick = ticks.get(i);
            for (Coordinate coord : updatesInTick.keySet()){
                result[coord.x()][coord.y()] = new Cell(updatesInTick.get(coord));
            }
        }
        return result;
    }

    public Map<Coordinate, Cell> getTickDelta(int tick){
        return ticks.getOrDefault(tick, null);
    }

    public void setCurrentTick(int tick){
        currentTick = tick;
    }

    @Override
    public void newUpdate(Notifier o) {
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
