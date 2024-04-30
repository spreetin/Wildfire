package se.oru.wildfire;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainWindow {

    View view;
    Controller controller;
    PaintManager paintManager = new PaintManager();
    SimulationController simulationController = new SimulationController();

    public MainWindow(){
        Stage newWindow = new Stage();
        newWindow.setTitle("Wildfire Simulator");

        // Create view
        view = new View();
        view.setMaxWidth(Double.MAX_VALUE);
        view.setMaxHeight(Double.MAX_VALUE);

        // Set up controls elements
        Pane painterGroup = paintManager.createLayout();
        Pane simulationGroup = simulationController.createLayout();
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        VBox rightPane = new VBox(painterGroup, spacer, simulationGroup);

        // Main layout
        HBox.setHgrow(view, Priority.ALWAYS);
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.VERTICAL);
        HBox horizontalSplit = new HBox(view, hSeparator, rightPane);
        newWindow.setScene(new Scene(horizontalSplit));
        newWindow.setMinHeight(700);
        newWindow.setMinWidth(1000);

        controller = new Controller(view);
        newWindow.show();
    }
}
