module edu.hedspi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires com.google.gson;
    requires json.simple;
    opens edu.main to javafx.fxml, com.google.gson, json.simple;

    opens edu.server.test to com.google.gson;
    opens edu.common.packet to com.google.gson;
    opens edu.common.packet.server to com.google.gson;
    opens edu.common.packet.client to com.google.gson;

    exports edu.server.test;
    exports edu.main;
}