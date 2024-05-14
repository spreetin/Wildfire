package se.oru.wildfire;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SimulationController {

    private Button playButton;
    private Button pauseButton;
    private Slider animationSlider;

    Pane createLayout(Controller controller){
    Pane createLayout(){
        String css = this.getClass().getResource("/styles.css").toExternalForm();
        // Animation controls
        ExtendedButton playButton = new ExtendedButton("Play");
        playButton.getStyleClass().add("button");
        playButton.getStyleClass().add("button-play");
        playButton.setOnMousePressed(_ -> {
            playButton.buttonActive(currentButton);
            currentButton = playButton;
        });
        playButton.setOnAction(event -> run());

        ExtendedButton pauseButton = new ExtendedButton("Pause");
        pauseButton.getStyleClass().add("button");
        pauseButton.getStyleClass().add("button-stop");
        pauseButton.setOnMousePressed(_ -> {
            pauseButton.buttonActive(currentButton);
            currentButton = pauseButton;
        });
        pauseButton.setOnAction(event -> pause());

        ExtendedButton windEnabler = new ExtendedButton("Wind");
        pauseButton.getStyleClass().add("button");
        pauseButton.getStyleClass().add("button-stop");
        pauseButton.setOnMousePressed(_ -> {
            pauseButton.buttonActive(currentButton);
            currentButton = pauseButton;
        });
        pauseButton.setOnAction(event -> windControl());

        Label animationControlLabel = new Label("Simulation Controls");
        animationControlLabel.getStyleClass().add("label");
        animationControlLabel.setAlignment(Pos.CENTER);
        animationControlLabel.setMaxWidth(Double.MAX_VALUE);

        Label windControlLabel = new Label("Wind");
        windControlLabel.getStyleClass().add("label");
        windControlLabel.setAlignment(Pos.CENTER);
        windControlLabel.setMaxWidth(Double.MAX_VALUE);

        HBox animationControlButtons = new HBox(playButton, pauseButton);
        animationControlButtons.setAlignment(Pos.CENTER);
        animationControlButtons.setSpacing(10);

        VBox layout = new VBox(windControlLabel, windEnabler, animationControlLabel, animationControlButtons);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        // Animation slider
        animationSlider = new Slider();
        animationSlider.setMinWidth(230);
        animationSlider.setPadding(new Insets(0, 0, 40, 0));

        // Event handlers
        playButton.setOnAction(actionEvent -> controller.startTimer());
        pauseButton.setOnAction(actionEvent -> controller.pauseTimer());

        // If we have wind -> controller.setWind(boolean)
        // Wind direction -> controller.setWindDirection(Calculator.WindDirection)
        VBox simulationGroup= new VBox(layout, animationSlider);
        simulationGroup.getStylesheets().add(css);
        simulationGroup.setSpacing(10);

        return simulationGroup;
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
