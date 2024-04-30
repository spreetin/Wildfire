package se.oru.wildfire;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SimulationController {

    Pane createLayout(){
        // Animation controls
        Button playButton = new Button("Play");
        Button pauseButton = new Button("Pause");
        HBox animationControlButtons = new HBox(playButton, pauseButton);
        animationControlButtons.setAlignment(Pos.CENTER);

        // Animation slider
        Slider animationSlider = new Slider();
        animationSlider.setMinWidth(230);
        animationSlider.setPadding(new Insets(0, 0, 40, 0));

        VBox container = new VBox(animationControlButtons, animationSlider);
        return container;
    }

    public void run(){
        // TODO: Implement
    }

    public void pause(){
        // TODO: Implement
    }

    public void setWindSpeed(float value){
        // TODO: Implement
    }

    public void setWindDirection(int angle){
        // TODO: Implement
    }
}
