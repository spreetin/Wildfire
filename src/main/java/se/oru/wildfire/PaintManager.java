package se.oru.wildfire;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PaintManager {
    private ExtendedButton currentButton;

    Pane createLayout(View view){
        // Painters
        String css = this.getClass().getResource("/styles.css").toExternalForm();
        // Trees
        ExtendedButton treeButton = new ExtendedButton("Trees");
        treeButton.getStyleClass().add("button");
        treeButton.getStyleClass().add("button-tree");
        treeButton.setOnAction(actionEvent -> setColorType(Cell.GroundType.Trees, view));
        treeButton.setOnMousePressed(_ -> {
            treeButton.buttonActive(currentButton);
            currentButton = treeButton;
        });

        // Water
        ExtendedButton waterButton = new ExtendedButton("Water");
        waterButton.getStyleClass().add("button");
        waterButton.getStyleClass().add("button-water");
        waterButton.setOnAction(actionEvent -> setColorType(Cell.GroundType.Water, view));
        waterButton.setOnMousePressed(_ -> {
            waterButton.buttonActive(currentButton);
            currentButton = waterButton;
        });
        // Stone
        ExtendedButton stoneButton = new ExtendedButton("Stone");
        stoneButton.getStyleClass().add("button");
        stoneButton.getStyleClass().add("button-stone");
        stoneButton.setOnAction(actionEvent -> setColorType(Cell.GroundType.Stone, view));
        stoneButton.setOnMousePressed(_ -> {
            stoneButton.buttonActive(currentButton);
            currentButton = stoneButton;
        });

        Label buttonLabel = new Label("Paint Control");
        buttonLabel.setMaxWidth(Double.MAX_VALUE);
        buttonLabel.setAlignment(Pos.CENTER);
        buttonLabel.getStyleClass().add("label");

        // Brush Slider
        Slider brushSizeSlider = new Slider(0,10,0);
        brushSizeSlider.setMajorTickUnit(2);
        brushSizeSlider.setShowTickMarks(true);
        brushSizeSlider.setMinorTickCount(0);
        brushSizeSlider.setSnapToTicks(true);
        brushSizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            view.setBrushSize(newVal.intValue());
        });
        brushSizeSlider.getStyleClass().add("brush-slider");

        Label brushSizeLabel = new Label("Brush Size");
        brushSizeLabel.getStyleClass().add("label");
        brushSizeLabel.setAlignment(Pos.CENTER);
        brushSizeLabel.setMaxWidth(Double.MAX_VALUE);

        // Brush Slider Group
        VBox brushSliderWrapper = new VBox(brushSizeLabel,brushSizeSlider);
        brushSliderWrapper.setAlignment(Pos.CENTER);
        brushSliderWrapper.setSpacing(10);

        // Buttons Group
        HBox buttonsGroup = new HBox(treeButton,waterButton,stoneButton);
        buttonsGroup.setAlignment(Pos.CENTER);
        buttonsGroup.setSpacing(5);

        // Buttons Wrapper
        VBox buttonsGroupWrapper = new VBox(buttonLabel,buttonsGroup);
        buttonsGroupWrapper.setAlignment(Pos.CENTER);
        buttonsGroupWrapper.setSpacing(10);

        // Painter Group
        VBox painterGroup = new VBox(buttonsGroupWrapper, brushSliderWrapper);
        painterGroup.setAlignment(Pos.CENTER);
        painterGroup.setSpacing(10);
        painterGroup.setMaxHeight(Double.MAX_VALUE);
        painterGroup.setPadding(new Insets(20, 0, 0, 0));

        painterGroup.getStylesheets().add(css);
        return painterGroup;
    }


    private void setColorType(Cell.GroundType groundType, View view){
        switch(groundType){
            case Trees -> view.setGroundType(Color.DARKGREEN);
            case Stone -> view.setGroundType(Color.DIMGRAY);
            case Water -> view.setGroundType(Color.DARKBLUE);
            default -> {
            }
        }
    }
}
