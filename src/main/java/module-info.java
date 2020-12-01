module edu.hedspi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires gson;

    requires json.simple;
    opens edu.main to javafx.fxml, gson, json.simple;
    exports edu.main;
}