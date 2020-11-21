module edu.hedspi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens edu.main to javafx.fxml;
    exports edu.main;
}