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

    private Slider animationSlider;
    private ExtendedButton currentButton;
    private Boolean windControl = false;
    private ExtendedButton currentWindDirection;

    Pane createLayout(Controller controller){
        String css = this.getClass().getResource("/styles.css").toExternalForm();

        // Animation controls
        ExtendedButton playButton = new ExtendedButton("Play", "play");
        playButton.setOnMousePressed(event -> {
            playButton.buttonActive(currentButton);
            currentButton = playButton;
        });
        playButton.setOnAction(event -> controller.startTimer());

        ExtendedButton pauseButton = new ExtendedButton("Pause", "pause");
        pauseButton.setOnMousePressed(event -> {
            pauseButton.buttonActive(currentButton);
            currentButton = pauseButton;
        });
        pauseButton.setOnAction(event -> controller.pauseTimer());

        // Wind Controls
        ExtendedButton windEnabler = new ExtendedButton("Wind Off", "wind");
        windEnabler.setOnMousePressed(event -> {
            windControl = !windControl;
            windEnabler.buttonStayActive();
            if(windControl){
                windEnabler.setText("Wind On");
            }else{
                windEnabler.setText("Wind Off");
            }
        });
        windEnabler.setOnAction(event -> {
            controller.setWind(windControl);
        });

        ExtendedButton windNorth = new ExtendedButton("North", "wind-direction");
        windNorth.setOnMousePressed(event -> {
            windNorth.buttonActive(currentButton);
            currentButton = windNorth;
        });
        windNorth.setOnAction(event -> {
            controller.setWindDirection(Calculator.WindDirection.North);
        });

        ExtendedButton windSouth = new ExtendedButton("South", "wind-direction");
        windSouth.setOnMousePressed(event -> {
            windSouth.buttonActive(currentButton);
            currentButton = windSouth;
        });
        windSouth.setOnAction(event -> {
            controller.setWindDirection(Calculator.WindDirection.South);
        });

        ExtendedButton windWest = new ExtendedButton("West", "wind-direction");
        windWest.setOnMousePressed(event -> {
            windWest.buttonActive(currentButton);
            currentButton = windWest;
        });
        windWest.setOnAction(event -> {
            controller.setWindDirection(Calculator.WindDirection.West);
        });

        ExtendedButton windEast = new ExtendedButton("East", "wind-direction");
        windEast.setOnMousePressed(event -> {
            windEast.buttonActive(currentButton);
            currentButton = windEast;
        });
        windEast.setOnAction(event -> {
            controller.setWindDirection(Calculator.WindDirection.East);
        });


        // Labelling
        Label animationControlLabel = new Label("Simulation Controls");
        animationControlLabel.getStyleClass().add("label");
        animationControlLabel.setAlignment(Pos.CENTER);
        animationControlLabel.setMaxWidth(Double.MAX_VALUE);

        Label windControlLabel = new Label("Wind Control");
        windControlLabel.getStyleClass().add("label");
        windControlLabel.setAlignment(Pos.CENTER);
        windControlLabel.setMaxWidth(Double.MAX_VALUE);


        // Setup
        HBox animationControlButtons = new HBox(playButton, pauseButton);
        animationControlButtons.setAlignment(Pos.CENTER);
        animationControlButtons.setSpacing(10);

        HBox NorthWest = new HBox(windNorth, windWest);
        NorthWest.setSpacing(10);
        HBox SouthEast = new HBox(windSouth, windEast);
        SouthEast.setSpacing(10);

        VBox windDirection = new VBox(NorthWest,SouthEast);
        windDirection.setSpacing(10);

        HBox windDirectionControl = new HBox(windDirection, windEnabler);
        windDirectionControl.setSpacing(10);
        windDirectionControl.setAlignment(Pos.CENTER);

        VBox layout = new VBox(windControlLabel, windDirectionControl, animationControlLabel, animationControlButtons);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        // Animation slider
        animationSlider = new Slider();
        animationSlider.setMinWidth(230);
        animationSlider.setPadding(new Insets(0, 0, 40, 0));

        VBox simulationGroup= new VBox(layout, animationSlider);
        simulationGroup.getStylesheets().add(css);
        simulationGroup.setSpacing(10);

        return simulationGroup;
    }

    public void addTick(){
        animationSlider.setMax(animationSlider.getValue()+1);
        animationSlider.setValue(animationSlider.getMax());
    }
}
