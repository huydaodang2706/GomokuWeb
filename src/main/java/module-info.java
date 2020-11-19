module edu.hedspi {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.main to javafx.fxml;
    exports edu.main;
}