package se.oru.wildfire;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PaintManager {

    Pane createLayout(){
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

        return painterGroup;
    }
}
