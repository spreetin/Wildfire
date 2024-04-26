package se.oru.wildfire;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;

public class MainWindow {

    View view;
    Controller controller;

    public MainWindow(){
        Stage newWindow = new Stage();
        newWindow.setTitle("Wildfire Simulator");

        // Create view
        view = new View();
        view.setMaxWidth(Double.MAX_VALUE);
        view.setMaxHeight(Double.MAX_VALUE);

        // Painters
        // Trees
        Button treeButton = new Button("Trees");
        treeButton.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, null, null)));
        treeButton.setTextFill(Color.WHITE);

        // Water
        Button waterButton = new Button("Water");
        waterButton.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));
        waterButton.setTextFill(Color.WHITE);

        // Stone
        Button stoneButton = new Button("Stone");
        stoneButton.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
        stoneButton.setTextFill(Color.WHITE);

        // Aggregate painters
        HBox painterGroup = new HBox(treeButton, waterButton, stoneButton);
        painterGroup.setAlignment(Pos.CENTER);
        painterGroup.setMaxHeight(Double.MAX_VALUE);
        painterGroup.setPadding(new Insets(40, 0, 0, 0));

        // Animation controls
        Button playButton = new Button("Play");
        Button pauseButton = new Button("Pause");
        HBox animationControlButtons = new HBox(playButton, pauseButton);
        animationControlButtons.setAlignment(Pos.CENTER);

        // Animation slider
        Slider animationSlider = new Slider();
        animationSlider.setMinWidth(230);
        animationSlider.setPadding(new Insets(0, 0, 40, 0));

        // Controls to the right
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        VBox rightPane = new VBox(painterGroup, spacer, animationControlButtons, animationSlider);


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
