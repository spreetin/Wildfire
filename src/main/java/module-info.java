module se.oru.wildfire {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.desktop;
    opens se.oru.wildfire to javafx.fxml;
    exports se.oru.wildfire;
}
