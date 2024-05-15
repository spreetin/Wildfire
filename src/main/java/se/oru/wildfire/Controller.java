package se.oru.wildfire;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    final View view ;
    final Model model = new Model();
    final Calculator calculator = new Calculator();
    final FrameStatistics frameStatistics = new FrameStatistics();
    final ReportGenerator reportGenerator = new ReportGenerator(frameStatistics);

    final Timer timer = new Timer(500, this);

    public Controller(View view){
        this.view = view;
        model.registerListener(view);
        model.registerListener(frameStatistics);
        model.registerListener(calculator);
        calculator.registerListener(model);
        setMapSize(50,50);
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
        calculator.needUpdate();
    }

    public void setWindDirection(Calculator.WindDirection windDirection){
        calculator.setWindDirection(windDirection);
    }

    public void setWind(boolean wind){
        calculator.setHasWind(wind);
    }
}
