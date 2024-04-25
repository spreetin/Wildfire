package se.oru.wildfire;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Stage newWindow = new Stage();
        newWindow.setTitle("New Scene");
        HBox horizontalSplit = new HBox();
        newWindow.setScene(new Scene(horizontalSplit));
        newWindow.show();
    }
}
