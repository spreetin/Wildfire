package se.oru.wildfire;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PaintManager {

    Pane createLayout(){
        // Painters
        // Trees
        Button treeButton = new Button("Trees");
        treeButton.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, new CornerRadii(3), new Insets(2))));
        treeButton.setTextFill(Color.WHITE);

        // Water
        Button waterButton = new Button("Water");
        waterButton.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, new CornerRadii(3), new Insets(2))));
        waterButton.setTextFill(Color.WHITE);

        // Stone
        Button stoneButton = new Button("Stone");
        stoneButton.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(3), new Insets(2))));
        stoneButton.setTextFill(Color.WHITE);

        // Aggregate painters
        HBox painterGroup = new HBox(treeButton, waterButton, stoneButton);
        painterGroup.setAlignment(Pos.CENTER);
        painterGroup.setMaxHeight(Double.MAX_VALUE);
        painterGroup.setPadding(new Insets(40, 0, 0, 0));

        return painterGroup;
    }
}
