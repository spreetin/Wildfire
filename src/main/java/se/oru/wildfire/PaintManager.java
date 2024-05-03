package se.oru.wildfire;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PaintManager {

    Pane createLayout(View view){
        // Painters
        // Trees
        Button treeButton = new Button("Trees");
        treeButton.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, new CornerRadii(3), new Insets(2))));
        treeButton.setTextFill(Color.WHITE);
        treeButton.setOnAction(actionEvent -> setColorType(Cell.GroundType.Trees, view));

        // Water
        Button waterButton = new Button("Water");
        waterButton.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(3), new Insets(2))));
        waterButton.setTextFill(Color.WHITE);
        waterButton.setOnAction(actionEvent -> setColorType(Cell.GroundType.Water, view));

        // Stone
        Button stoneButton = new Button("Stone");
        stoneButton.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(3), new Insets(2))));
        stoneButton.setTextFill(Color.WHITE);
        stoneButton.setOnAction(actionEvent -> setColorType(Cell.GroundType.Stone, view));

        // Brush Slider
        Label brushSizeLabel = new Label("Brush Size");
        Slider brushSizeSlider = new Slider(0,10,0);
        brushSizeSlider.setMajorTickUnit(2);
        brushSizeSlider.setShowTickMarks(true);
        brushSizeSlider.setMinorTickCount(0);
        brushSizeSlider.setSnapToTicks(true);
        brushSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            view.setBrushSize(newVal.intValue());
        });


        // Brush Slider Group
        VBox brushSliderGroup = new VBox(brushSizeLabel,brushSizeSlider);
        brushSliderGroup.setAlignment(Pos.CENTER);
        brushSliderGroup.setSpacing(10);

        // Buttons Group
        HBox buttonsGroup = new HBox(treeButton,waterButton,stoneButton);
        buttonsGroup.setAlignment(Pos.CENTER);

        // Painter Group
        VBox painterGroup = new VBox(buttonsGroup, brushSliderGroup);
        painterGroup.setAlignment(Pos.CENTER);
        painterGroup.setSpacing(25);
        painterGroup.setMaxHeight(Double.MAX_VALUE);
        painterGroup.setPadding(new Insets(40, 0, 0, 0));

        return painterGroup;
    }

    private void setColorType(Cell.GroundType groundType, View view){
        switch(groundType){
            case Trees -> view.setGroundType(Color.DARKGREEN);
            case Stone -> view.setGroundType(Color.DARKGREY);
            case Water -> view.setGroundType(Color.DARKBLUE);
            default -> {
            }
        }
    }
}
