package se.oru.wildfire;

import se.oru.wildfire.Calculator;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    final View view ;
    final Model model = new Model();
    final Calculator calculator = new Calculator();
    final FrameStatistics frameStatistics = new FrameStatistics();

    Timer timer = new Timer(1000, this);

    public Controller(View view){
        this.view = view;
        model.registerListener(view);
        model.registerListener(frameStatistics);
        model.registerListener(calculator);
        calculator.registerListener(model);
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
