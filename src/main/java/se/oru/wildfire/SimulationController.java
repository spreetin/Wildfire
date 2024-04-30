package se.oru.wildfire;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SimulationController {

    private Button playButton;
    private Button pauseButton;
    private Slider animationSlider;

    Pane createLayout(){
        // Animation controls
        playButton = new Button("Play");
        pauseButton = new Button("Pause");
        HBox animationControlButtons = new HBox(playButton, pauseButton);
        animationControlButtons.setAlignment(Pos.CENTER);

        // Animation slider
        animationSlider = new Slider();
        animationSlider.setMinWidth(230);
        animationSlider.setPadding(new Insets(0, 0, 40, 0));

        // Event handlers
        playButton.setOnAction(event -> run());
        pauseButton.setOnAction(event -> pause());

        return new VBox(animationControlButtons, animationSlider);
    }

    public void run(){
        // TODO: Implement
    }

    public void pause(){
        // TODO: Implement
    }

    public void addTick(){
        animationSlider.setMax(animationSlider.getValue()+1);
        animationSlider.setValue(animationSlider.getMax());
    }

    public void setWindSpeed(float value){
        // TODO: Implement
    }

    public void setWindDirection(int angle){
        // TODO: Implement
    }
}
