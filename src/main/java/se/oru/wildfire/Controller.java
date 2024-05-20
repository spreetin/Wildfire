package se.oru.wildfire;

import javafx.stage.Stage;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener {

    final View view;
    final Model model = new Model();
    final Calculator calculator = new Calculator();
    final FrameStatistics frameStatistics = new FrameStatistics();
    final ReportGenerator reportGenerator = new ReportGenerator(frameStatistics);
    final Timer timer = new Timer(500, this);
    final Stage stage;
    final ArrayList<TickObserver> tickObservers = new ArrayList<>();
    boolean tickChanged = false;

    public Controller(View view, Stage stage){
        this.stage = stage;
        this.view = view;
        model.registerListener(view);
        model.registerListener(frameStatistics);
        model.registerListener(calculator);
        calculator.registerListener(model);
        setMapSize(50,50);
    }

    public void addTickObserver(TickObserver tickObserver){
        tickObservers.add(tickObserver);
    }

    public void setMapSize(int width, int height) {
        Cell[][] cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setGroundType(Cell.GroundType.Trees);
            }
        }
        InitialMap initialMap = new InitialMap(cells);
        view.setInitialMap(initialMap);
        model.setInitialMap(initialMap);
    }

    public void setTickSpeed(int msecs){
        timer.setDelay(msecs);
    }

    public void startTimer(){
        if (frameStatistics.getCurrentTick() == 0){
            InitialMap initialMap = view.returnPaintedMap();
            model.setInitialMap(initialMap);
            frameStatistics.setStartingMap(initialMap.getMap());
            calculator.setInitialMap(initialMap);
        }
        timer.start();
    }

    public void pauseTimer(){
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (tickChanged){
            for (TickObserver tickObserver : tickObservers){
                tickObserver.setTick(frameStatistics.getCurrentTick());
            }
        }
        calculator.needUpdate();
        for (TickObserver tickObserver : tickObservers) {
            tickObserver.addTick();
        }
    }

    public void setWindDirection(Calculator.WindDirection windDirection){
        calculator.setWindDirection(windDirection);
    }

    public void setWind(boolean wind){
        calculator.setHasWind(wind);
    }

    public void resetWithMap(){
        setActiveTick(0);
    }

    public void setActiveTick(int tickNumber){
        if (frameStatistics.hasTick(tickNumber)){
            pauseTimer();
            Cell[][] tick = frameStatistics.getTick(tickNumber);
            InitialMap initialMap = new InitialMap(tick);
            view.setInitialMap(initialMap);
            model.setInitialMap(initialMap);
            calculator.setInitialMap(initialMap);
            frameStatistics.setCurrentTick(tickNumber);
            tickChanged = true;
        }
    }

    public void saveMap(){
        Cell[][] map = frameStatistics.getTick(0);
        if (map != null){
            MapFileHandler.saveMap(map, stage);
        }
    }

    public void loadMap(){
        Cell[][] map = MapFileHandler.loadMap(stage);
        InitialMap initialMap = new InitialMap(map);
        frameStatistics.setCurrentTick(0);
        view.setInitialMap(initialMap);
        model.setInitialMap(initialMap);
    }
}
