package se.oru.wildfire;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ExtendedButton extends Button {

    public ExtendedButton(String name, String style) {
        super(name);
        buttonHover();
        this.getStyleClass().add("button");
        this.getStyleClass().add("button-"+style);
    }

    public void buttonHover(){
        int timerButtonHover = 200;
        ScaleTransition incrementSize = new ScaleTransition(Duration.millis(timerButtonHover), this);
        incrementSize.setToX(1.1);
        incrementSize.setToY(1.1);

        ScaleTransition decrementSize = new ScaleTransition(Duration.millis(timerButtonHover), this);
        decrementSize.setToX(1.0);
        decrementSize.setToY(1.0);

        this.setOnMouseEntered(e -> incrementSize.playFromStart());
        this.setOnMouseExited(e -> decrementSize.playFromStart());
    }

    public void buttonActive(Button currentButton){
        int timerButtonPressed = 200;
        FadeTransition newFade = new FadeTransition(Duration.millis(timerButtonPressed), this);
        newFade.setFromValue(0.7);
        newFade.setToValue(1.0);

        if(currentButton != null){
            currentButton.getStyleClass().remove("active");
        }
        this.getStyleClass().add("active");
        newFade.play();
    }

    public void buttonStayActive(){
        int timerButtonPressed = 200;
        FadeTransition newFade = new FadeTransition(Duration.millis(timerButtonPressed), this);
        newFade.setFromValue(0.7);
        newFade.setToValue(1.0);

        if(this.getStyleClass().contains("active")){
            this.getStyleClass().remove("active");
        } else{
            this.getStyleClass().add("active");
        }
        newFade.play();
    }
}
