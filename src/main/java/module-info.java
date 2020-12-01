module edu.hedspi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires gson;

    opens edu.main to javafx.fxml,gson;
    exports edu.main;
}