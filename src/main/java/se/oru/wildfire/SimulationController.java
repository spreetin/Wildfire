package se.oru.wildfire;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SimulationController implements TickObserver{
    private final ObservableList<String> sizeOptions =
            FXCollections.observableArrayList(
                    "50x50",
                    "100x100",
                    "150x150",
                    "200x200"
            );
    private Slider animationSlider;
    private ExtendedButton currentButtonWind;
    private ExtendedButton currentButton;
    private Boolean windControl = false;
    private ExtendedButton currentWindDirection;
    boolean blockTickUpdate = false;
    private Boolean running = false;

    Pane createLayout(Controller controller){
        controller.addTickObserver(this);

        String css = this.getClass().getResource("/styles.css").toExternalForm();

        // Dropdown menu of sizes
        ComboBox<String> dropDownSizeMenu = new ComboBox<>(sizeOptions);
        dropDownSizeMenu.setValue("50x50");
        dropDownSizeMenu.setOnAction(event -> {
            String selectedSize = dropDownSizeMenu.getValue();
            switch(selectedSize){
                case "50x50":
                    controller.setMapSize(50,50);
                    break;
                case "100x100":
                    controller.setMapSize(100,100);
                    break;
                case "150x150":
                    controller.setMapSize(150,150);
                    break;
                case "200x200":
                    controller.setMapSize(200,200);
                    break;
                default:
                    break;
            }
        });
        dropDownSizeMenu.getStyleClass().add("combo-box");

        // Animation controls
        ExtendedButton playButton = new ExtendedButton("Play", "play");
        playButton.setOnMousePressed(event -> {
            playButton.buttonActive(currentButton);
            running = true;
            playButton.setText("Running");
            currentButton = playButton;
        });
        playButton.setOnAction(event -> controller.startTimer());

        ExtendedButton pauseButton = new ExtendedButton("Pause", "pause");
        pauseButton.setOnMousePressed(event -> {
            pauseButton.buttonActive(currentButton);
            if(running){
                running = false;
                playButton.setText("Play");
            }
            currentButton = pauseButton;
        });
        pauseButton.setOnAction(event -> controller.pauseTimer());

        ExtendedButton resetButton = new ExtendedButton("Reset", "reset");
        resetButton.setOnMousePressed(event -> {
            resetButton.buttonStayActive();
            if(running){
                running = false;
                playButton.setText("Play");

            }
            pauseButton.getStyleClass().remove("active");
            playButton.getStyleClass().remove("active");
        });
        resetButton.setOnAction(event -> {
            controller.resetWithMap();
            controller.pauseTimer();
        });

        // Wind Controls
        ExtendedButton windEnabler = new ExtendedButton("Wind Off", "wind");
        windEnabler.setOnMousePressed(event -> {
            windControl = !windControl;
            windEnabler.buttonStayActive();
            windEnabler.setText(windControl ? "Wind On" : "Wind Off");
        });
        windEnabler.setOnAction(event -> {
            controller.setWind(windControl);
        });

        ExtendedButton windNorth = new ExtendedButton("North", "wind-direction");
        windNorth.setOnMousePressed(event -> {
            windNorth.buttonActive(currentButtonWind);
            currentButtonWind = windNorth;
        });
        windNorth.setOnAction(event -> {
            controller.setWindDirection(Calculator.WindDirection.North);
        });

        ExtendedButton windSouth = new ExtendedButton("South", "wind-direction");
        windSouth.setOnMousePressed(event -> {
            windSouth.buttonActive(currentButtonWind);
            currentButtonWind = windSouth;
        });
        windSouth.setOnAction(event -> {
            controller.setWindDirection(Calculator.WindDirection.South);
        });

        ExtendedButton windWest = new ExtendedButton("West", "wind-direction");
        windWest.setOnMousePressed(event -> {
            windWest.buttonActive(currentButtonWind);
            currentButtonWind = windWest;
        });
        windWest.setOnAction(event -> {
            controller.setWindDirection(Calculator.WindDirection.West);
        });

        ExtendedButton windEast = new ExtendedButton("East", "wind-direction");
        windEast.setOnMousePressed(event -> {
            windEast.buttonActive(currentButtonWind);
            currentButtonWind = windEast;
        });
        windEast.setOnAction(event -> {
            controller.setWindDirection(Calculator.WindDirection.East);
        });

        // Report,save and load controls
        ExtendedButton exportReport = new ExtendedButton("Export", "report");
        exportReport.setOnMousePressed(event -> {
            exportReport.buttonActive(currentButton);
        });
        exportReport.setOnAction(event -> controller.reportGenerator.recordCounter());

        ExtendedButton saveMap = new ExtendedButton("Save", "report");
        saveMap.setOnMousePressed(event -> {
            saveMap.buttonActive(currentButton);
        });
        saveMap.setOnAction(event -> controller.saveMap());

        ExtendedButton loadMap = new ExtendedButton("Load", "report");
        loadMap.setOnMousePressed(event -> {
            loadMap.buttonActive(currentButton);
        });
        loadMap.setOnAction(event -> controller.loadMap());

        // Labelling
        Label mapResizerLabel = newLabel("Resize Map");
        Label windControlLabel = newLabel("Wind Control");
        Label animationControlLabel = newLabel("Simulation Controls");
        Label animationSliderLabel = newLabel("Animation Slider");
        Label reportGenerationLabel = newLabel("Report Generator");


        // Setup
        VBox mapResize = new VBox(mapResizerLabel,dropDownSizeMenu);
        mapResize.setSpacing(10);
        mapResize.setAlignment(Pos.CENTER);

        HBox northWest = new HBox(windNorth, windWest);
        northWest.setSpacing(10);
        HBox southEast = new HBox(windSouth, windEast);
        southEast.setSpacing(10);

        VBox windDirection = new VBox(northWest, southEast);
        windDirection.setSpacing(10);

        HBox windDirectionControl = new HBox(windDirection, windEnabler);
        windDirectionControl.setSpacing(10);
        windDirectionControl.setAlignment(Pos.CENTER);

        HBox animationControlButtons = new HBox(playButton, pauseButton, resetButton);
        animationControlButtons.setAlignment(Pos.CENTER);
        animationControlButtons.setSpacing(5);

        HBox fileReportButtons = new HBox(saveMap,loadMap,exportReport);
        fileReportButtons.setAlignment(Pos.CENTER);
        fileReportButtons.setSpacing(5);



        // animation Slider
        animationSlider = new Slider();
        animationSlider.setMajorTickUnit(20);
        animationSlider.setShowTickMarks(false);
        animationSlider.setMinorTickCount(0);
        animationSlider.setSnapToTicks(false);
        animationSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (!blockTickUpdate){
                    controller.setActiveTick(number.intValue());
                }
                blockTickUpdate = false;
            }
        });
        animationSlider.getStyleClass().add("slider");

        Label windIntensityLabel = new Label("Set wind speed");
        windIntensityLabel.getStyleClass().add("label-inline");
        Slider windIntensitySlider = new Slider(0.0,0.3,0.0);
        windIntensitySlider.setMajorTickUnit(0.01);
        windIntensitySlider.setShowTickMarks(false);
        windIntensitySlider.setMinorTickCount(0);
        windIntensitySlider.setSnapToTicks(false);
        windIntensitySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            controller.setWindIntensity(newVal.doubleValue());
        });
        windIntensitySlider.getStyleClass().add("slider");
        VBox windSlider = new VBox(windIntensityLabel, windIntensitySlider);
        windSlider.setAlignment(Pos.CENTER);

        Label animationSpeedSliderLabel = new Label("Set animation speed");
        animationSpeedSliderLabel.getStyleClass().add("label-inline");
        Slider animationSpeedSlider = new Slider(-300,-20,-100);
        animationSpeedSlider.setMajorTickUnit(20);
        animationSpeedSlider.setShowTickMarks(false);
        animationSpeedSlider.setMinorTickCount(0);
        animationSpeedSlider.setSnapToTicks(false);
        animationSpeedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            controller.setTickSpeed(-newVal.intValue());
        });
        animationSpeedSlider.getStyleClass().add("slider");
        VBox animationSpeed = new VBox(animationSpeedSliderLabel, animationSpeedSlider);
        animationSpeed.setAlignment(Pos.CENTER);

        VBox layout = new VBox(mapResize,
                windControlLabel,
                windDirectionControl,
                windSlider,
                animationControlLabel,
                animationControlButtons,
                animationSpeed,
                animationSliderLabel,
                animationSlider,
                reportGenerationLabel,
                fileReportButtons);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(5);

        VBox simulationGroup= new VBox(layout);
        simulationGroup.getStylesheets().add(css);
        simulationGroup.setSpacing(10);

        return simulationGroup;
    }

    @Override
    public void addTick(){
        blockTickUpdate = true;
        animationSlider.setMax(animationSlider.getValue()+1);
        animationSlider.setValue(animationSlider.getMax());
    }

    @Override
    public void setTick(int tick){
        blockTickUpdate = true;
        animationSlider.setMax(tick);
        animationSlider.setValue(tick);
    }

    private Label newLabel(String name){
        Label newLabel = new Label(name);
        newLabel.getStyleClass().add("label-banner");
        newLabel.setAlignment(Pos.CENTER);
        newLabel.setMaxWidth(Double.MAX_VALUE);
        return newLabel;
    }

}
