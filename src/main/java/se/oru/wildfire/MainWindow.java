package se.oru.wildfire;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainWindow {

    final View view;
    final Controller controller;
    final PaintManager paintManager;
    final SimulationController simulationController = new SimulationController();

    public MainWindow(){
        Stage newWindow = new Stage();
        newWindow.setTitle("Wildfire Simulator");

        // Create view
        view = new View();
        paintManager = new PaintManager();
        view.setMaxWidth(Double.MAX_VALUE);
        view.setMaxHeight(Double.MAX_VALUE);

        // Set up controls elements
        Pane painterGroup = paintManager.createLayout(view);
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
