package se.oru.wildfire;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        //view.setMaxWidth(Double.MAX_VALUE);
        //view.setMaxHeight(Double.MAX_VALUE);
        controller = new Controller(view, newWindow);

        // Set up controls elements
        Pane painterGroup = paintManager.createLayout(view);
        Pane simulationGroup = simulationController.createLayout(controller);
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        VBox rightPane = new VBox(painterGroup, spacer, simulationGroup);

        // Main layout
        Separator hSeparator = new Separator();
        hSeparator.setOrientation(Orientation.VERTICAL);
        Pane viewContainer = new Pane(view);
        viewContainer.setMaxHeight(Double.MAX_VALUE);
        viewContainer.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(viewContainer, Priority.ALWAYS);
        viewContainer.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> view.resize());
        viewContainer.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> view.resize());
        HBox horizontalSplit = new HBox(viewContainer, hSeparator, rightPane);
        newWindow.setScene(new Scene(horizontalSplit));
        newWindow.setMinHeight(700);
        newWindow.setMinWidth(1000);
        newWindow.show();
    }
}
