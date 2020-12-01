module edu.hedspi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires com.google.gson;
    requires json.simple;
    opens edu.main to javafx.fxml, com.google.gsonc, json.simple;
    opens edu.server.Test to com.google.gson;
    exports edu.server.Test;
    exports edu.main;
}