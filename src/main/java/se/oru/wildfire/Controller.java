package se.oru.wildfire;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    final View view ;
    final Model model = new Model();
    final Calculator calculator = new Calculator();
    final FrameStatistics frameStatistics = new FrameStatistics();

    final Timer timer = new Timer(1000, this);

    public Controller(View view){
        this.view = view;
        model.registerListener(view);
        model.registerListener(frameStatistics);
        model.registerListener(calculator);
        calculator.registerListener(model);
        Cell[][] cells = new Cell[50][50];
        for (int i=0; i<50; i++){
            for (int j=0; j<50; j++){
                cells[i][j] = new Cell();
                cells[i][j].setGroundType(Cell.GroundType.Trees);
            }
        }
        InitialMap initialMap = new InitialMap(cells);
        view.setInitialMap(initialMap);
    }

    public void setTickSpeed(int msecs){
        timer.setDelay(msecs);
    }

    public void startTimer(){
        timer.start();
    }

    public void pauseTimer(){
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        calculator.needUpdate();
    }
}
